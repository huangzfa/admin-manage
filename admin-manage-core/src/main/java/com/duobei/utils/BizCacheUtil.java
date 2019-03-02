/*
 *@Copyright (c) 2016, 杭州霖梓网络科技股份有限公司 All Rights Reserved. 
 */
package com.duobei.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;


/**
 *
 *@类描述：缓存服务
 *@author 何鑫 2017年1月18日  12:51:33
 *@注意：本内容仅限于杭州蒲公英数据科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class BizCacheUtil {
    protected static Logger logger           = LoggerFactory.getLogger(BizCacheUtil.class);

    public static boolean BIZ_CACHE_SWITCH = true;//业务缓存开关，true：打开（即使用缓存）  false：关闭（即不使用缓存）

	public static final long SECOND_OF_TEN_MINITS = 10 * 60L;

	public static final long FORTY_FIVE_SECOND =  45L;

	public static final long SECOND_OF_THREE = 30L;// 30秒

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存到缓存，过期时间为默认过期时间
     *
     * @param key 缓存key
     * @param seriObj 缓存数据
     */
    public void saveObject(final String key, final Serializable seriObj){
        this.saveObject(key, seriObj, SECOND_OF_TEN_MINITS);
    }

    /**
     * 保存到缓存，不设过期时间
     *
     * @param key 缓存key
     * @param seriObj 缓存数据
     */
    public void saveObjectWithOutExpire(final String key, final Serializable seriObj) {
        if(!BIZ_CACHE_SWITCH || StringUtils.isBlank(key) || seriObj == null){
            return ;
        }
        try{
	        redisTemplate.execute(new RedisCallback<Object>() {
	            @Override
	            public Object doInRedis(RedisConnection connection) throws DataAccessException {
	                connection.set(redisTemplate.getStringSerializer().serialize(key), SerializeUtil.serialize(seriObj));
	                return null;
	            }
	        });
        }catch(Exception e){
        	logger.error("saveObjectWithOutExpire",e);
        }
    }

    /**
     * 保存到缓存，并设定过期时间
     *
     * @param key 缓存key
     * @param seriObj 缓存数据
     * @param expire 过期时间
     */
    public void saveObject(final String key, final Serializable seriObj,final long expire) {
        if(!BIZ_CACHE_SWITCH || StringUtils.isBlank(key) || seriObj == null){
            return ;
        }
        try{
	        redisTemplate.execute(new RedisCallback<Object>() {
	            @Override
	            public Object doInRedis(RedisConnection connection) throws DataAccessException {
	                connection.set(redisTemplate.getStringSerializer().serialize(key), SerializeUtil.serialize(seriObj));
	                connection.expire(redisTemplate.getStringSerializer().serialize(key), expire);
	                return null;
	            }
	        });
        }catch(Exception e){
        	logger.error("saveObject",e);
        }
    }

    /**
     * 获取缓存对象
     * @param key 缓存key
     * @return
     */
    public Object getObject(final String key) {
        if (!BIZ_CACHE_SWITCH || StringUtils.isBlank(key)) {
            return null;
        }
        try{
	        return redisTemplate.execute(new RedisCallback<Object>() {
	            @Override
	            public Object doInRedis(RedisConnection connection) throws DataAccessException {
	            	byte[] getResult = connection.get(redisTemplate.getStringSerializer().serialize(key));
	            	if(getResult == null || getResult.length == 0){
	            		return null;
	            	}
	                return  SerializeUtil.unserialize(getResult);
	            }
	        });
        }catch(Exception e){
        	logger.error("getObject error",e);
        	return null;
        }
    }

    /**
     * 锁住某个key值，需要解锁时删除即可
     * @param key
     * @param value
     * @return
     */
    public boolean getLock(final String key, final String value) {
        if(!BIZ_CACHE_SWITCH){
            return false;
        }
        try{
	        return (Boolean)redisTemplate.execute(new RedisCallback<Object>() {
	            @Override
	            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
	                return connection.setNX(redisTemplate.getStringSerializer().serialize(key), value.getBytes());
	            }
	        });
        }catch(Exception e){
        	logger.error("getLock error",e);
        	return false;
        }
    }

    /**
     * 重试多次来获取锁，重试times次
     * @param key 锁对应的key
     * @param value 锁对应的value
     * @param times 重试次数
     * @return
     */
    public boolean getLockTryTimes(String key, String value, int times) {
        try {
            if (!BIZ_CACHE_SWITCH) {
                return false;
            }
            if (times < 2) {
                return getLock(key, value);
            }
            for (int i = 0; i < times; i++) {
                Thread.sleep(RandomUtils.nextInt(10));
                boolean ifGet = getLock(key, value);
                if (ifGet) {
                    return true;
                }
                if (i == times - 1) {
                    this.delCache(key);
                }
            }
        } catch (Exception e) {
            logger.error("get object error", e);
            return false;
        }
        return false;
    }

    /**
     * 删除缓存
     * @param key 需要删除缓存的id
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void delCache(final String key) {
        if (!BIZ_CACHE_SWITCH) {
            return;
        }
        try{
	        redisTemplate.execute(new RedisCallback() {
	            public Long doInRedis(RedisConnection connection) throws DataAccessException {
	                return connection.del(redisTemplate.getStringSerializer().serialize(key));
	            }
	        });
        }catch(Exception e){
        	logger.error("delCache error",e);

        }
    }

    /**
     * 数据类型为List的数据写入缓存
     * @param key 写入缓存数据的key
     * @param seriObjList 需要写入缓存的数据
     */
    public <T> void saveObjectList(final String key, final List<T> seriObjList) {
    	this.saveObjectListExpire(key, seriObjList, SECOND_OF_TEN_MINITS);
    }

    /**
     * 数据类型为List的数据写入缓存
     * @param key 写入缓存数据的key
     * @param seriObjList 需要写入缓存的数据
     */
    public <T> void saveObjectListExpire(final String key, final List<T> seriObjList,final Long expire) {
    	try{
	        if (!BIZ_CACHE_SWITCH || StringUtils.isBlank(key) || seriObjList == null || seriObjList.size() < 1) {
	            return;
	        }
	        redisTemplate.execute(new RedisCallback<Object>() {
	            @Override
	            public Object doInRedis(RedisConnection connection) throws DataAccessException {
	                connection.set(redisTemplate.getStringSerializer().serialize(key), SerializeUtil.serializeList(seriObjList));
	                connection.expire(redisTemplate.getStringSerializer().serialize(key), expire);
	                return null;
	            }
	        });
    	}catch(Exception e){
    		logger.error("saveObjectListExpire" + key,e);

    	}
    }

    /**
     * 获取数据类型为List的缓存数据
     * @param key 缓存数据对应的key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getObjectList(final String key) {
    	try{
	        if (!BIZ_CACHE_SWITCH || StringUtils.isBlank(key)) {
	            return null;
	        }
	        Object result = redisTemplate.execute(new RedisCallback<Object>() {
	            @Override
	            public Object doInRedis(RedisConnection connection) throws DataAccessException {
	                byte[] retBytes = connection.get(redisTemplate.getStringSerializer().serialize(key));
	                if(retBytes == null || retBytes.length ==0){
	                	return null;
	                }
	                return SerializeUtil.unserializeList(retBytes);
	            }
	        });
	        return (List<T>) result;
    	}catch(Exception e){
    		logger.error("getObjectList error" + key,e);;
    		return null;
    	}
    }

    /**
     * 该接口只为检测缓存健康使用
     *
     * @param key
     * @param seriObj
     * @param expire
     * @return
     */
    public boolean putObjectForCheckHeath(final String key, final Serializable seriObj, final long expire) {
    	try{
	        redisTemplate.execute(new RedisCallback<Object>() {
	            @Override
	            public Object doInRedis(RedisConnection connection) throws DataAccessException {
	                connection.set(redisTemplate.getStringSerializer().serialize(key), SerializeUtil.serialize(seriObj));
	                connection.expire(redisTemplate.getStringSerializer().serialize(key), expire);
	                return null;
	            }
	        });
    	}catch(Exception e){
    		logger.error("putObjectForCheckHeath error",e);
    		return false;
    	}
        return true;
    }

    /**
     * 该接口只为检测缓存健康使用
     * @param key
     * @return
     */
    public Object getObjectForCheckHeath(final String key) {
    	try{
	        return redisTemplate.execute(new RedisCallback<Object>() {
	            @Override
	            public Object doInRedis(RedisConnection connection) throws DataAccessException {
	            	byte[] retBytes = connection.get(redisTemplate.getStringSerializer().serialize(key));
	            	if(retBytes == null || retBytes.length == 0){
	            		return null;
	            	}
	                return  SerializeUtil.unserialize(retBytes);
	            }
	        });
    	}catch(Exception e){
    		logger.error("getObjectForCheckHeath err",e);
    		return null;
    	}
    }

    /**
     * 自增
     * @param cacheKey
     */
    public void incr(final String cacheKey) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Long num = connection.incr(redisTemplate.getStringSerializer().serialize(cacheKey));
                return num;
            }
        });
    }

    /**
     * 锁住某个key值45S，需要解锁时删除即可
     *
     * @param key
     * @param value
     * @return
     */
    public boolean getLock45Second(final String key, final String value) {
        if (!BIZ_CACHE_SWITCH) {
            return false;
        }
        try {
            Boolean flag = (Boolean) redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.setNX(redisTemplate.getStringSerializer().serialize(key), value.getBytes());
                }
            });
            if (flag) {
                redisTemplate.execute(new RedisCallback<Object>() {
                    @Override
                    public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                        return connection.expire(redisTemplate.getStringSerializer().serialize(key), FORTY_FIVE_SECOND);
                    }
                });
            }
            return flag;
        } catch (Exception e) {
            logger.error("getLock error", e);
            return false;
        }
    }

	/**
	 * 锁住某个key值30S，需要解锁时删除即可
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean getLock30Second(final String key, final String value) {
		if (!BIZ_CACHE_SWITCH) {
			return false;
		}
		try {
			Boolean flag = (Boolean) redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					return connection.setNX(redisTemplate.getStringSerializer().serialize(key), value.getBytes());
				}
			});
			if (flag) {
				redisTemplate.execute(new RedisCallback<Object>() {
					@Override
					public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
						return connection.expire(redisTemplate.getStringSerializer().serialize(key), SECOND_OF_THREE);
					}
				});
			}
			return flag;
		} catch (Exception e) {
			logger.error("getLock error", e);
			return false;
		}
	}

	public void hSet(final String key, final String hashKey, final Serializable value) {
		try {
			final byte[] rawKey;
			final byte[] rawHashKey;
			rawKey = key.getBytes("UTF-8");
			rawHashKey = hashKey.getBytes("UTF-8");
			final byte[] rawHashValue = SerializeUtil.serialize(value);
			redisTemplate.execute(connection -> {
				connection.hSet(rawKey, rawHashKey, rawHashValue);
				return null;
			}, true);
		} catch (UnsupportedEncodingException e) {
			logger.debug("BizCacheUtil hashSet ", e);
		}
	}

	public Object hGet(final String key, final String hashKey) {
		return redisTemplate.execute((RedisCallback<Object>) connection -> {
			byte[] getResult = connection.hGet(
					redisTemplate.getStringSerializer().serialize(key),
					redisTemplate.getStringSerializer().serialize(hashKey)
			);
			if (getResult == null || getResult.length == 0){ return null;}
			return SerializeUtil.unserialize(getResult);
		});
	}

	public boolean hExists(final String key, final String hashKey) {
		try {
			final byte[] rawKey;
			final byte[] rawHashKey;
			rawKey = key.getBytes("UTF-8");
			rawHashKey = hashKey.getBytes("UTF-8");
			return (boolean) redisTemplate.execute((RedisCallback<Object>) connection -> connection.hExists(rawKey, rawHashKey));
		} catch (UnsupportedEncodingException e) {
			logger.debug("BizCacheUtil hashGet ", e);
			return false;
		}
	}

	public boolean hDel(final String key, final String hashKey) {
		try {
			final byte[] rawKey;
			final byte[] rawHashKey;
			rawKey = key.getBytes("UTF-8");
			rawHashKey = hashKey.getBytes("UTF-8");
			Long execute = (Long) redisTemplate.execute((RedisCallback<Object>) connection -> connection.hDel(rawKey, rawHashKey));
			return execute > 0;
		} catch (UnsupportedEncodingException e) {
			logger.debug("BizCacheUtil hashGet ", e);
			return false;
		}
	}

	private static final String LOCK_SUCCESS = "OK";
	//NX 表示key不存在时才set XX 代表 key存在是才set
	private static final String SET_IF_NOT_EXIST = "NX";
	//PX 代表毫秒  EX 代表秒
	private static final String SET_WITH_EXPIRE_TIME = "PX";
	private static final Long RELEASE_SUCCESS = 1L;

	/**
	 * 获取 redis 锁
	 * @param lockKey
	 * @param requestValue
	 * @param expireTime 锁的过期时间
	 * @return
	 */
	public boolean getRedisLock(final String lockKey,final String requestValue,final long expireTime) {
		if (!BIZ_CACHE_SWITCH) {
			return false;
		}
		Boolean flag = false;
		try {
			//如果获取不到锁  那么就不执行
			flag = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					Jedis jedis = (Jedis) connection.getNativeConnection();

					String result = jedis.set(lockKey, requestValue, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
					if (LOCK_SUCCESS.equals(result)) {
						return true;
					}
					return false;
				}
			});
		}catch(Exception e){
			logger.error("获取redis锁异常!key:"+lockKey,e);
			flag = false;
		}
		return flag;
	}

	/**
	 * 通过键 值 手动释放锁
	 * @param lockKey
	 * @param requestValue
	 * @return
	 */
	public boolean expireRedisLock( String lockKey, String requestValue) {
		if (!BIZ_CACHE_SWITCH) {
			return false;
		}
		Boolean flag = false;
		try {
			flag = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					Jedis jedis = (Jedis) connection.getNativeConnection();
					String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
					Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestValue));

					if (RELEASE_SUCCESS.equals(result)) {
						return true;
					}
					return false;
				}
			});
		}catch(Exception e){
			logger.error("手动释放redis锁异常,等待自动释放!key:"+lockKey,e);
			flag = false;
		}
		return flag;
	}

	/**
	 * 重复获取redis锁  直到获取到 或者是达到最大次数为止 每隔10s获取一次
	 * @param lockKey
	 * @param requestValue
	 * @param expireTime
	 * @param maxRepeat 大于等于 1
	 * @return
	 */
	public boolean repeatGetRedisLock(final String lockKey,final String requestValue,final long expireTime,int maxRepeat) {
		if (!BIZ_CACHE_SWITCH) {
			return false;
		}
		Boolean flag = false;
		int repeat = 0;
		if(maxRepeat < 1){
			maxRepeat = 1;
		}
		try {

			while(!flag){
				//达到最大获取次数跳出循环
				if(repeat >= maxRepeat){
					break;
				}

				//如果获取不到锁  那么就不执行
				flag = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
					@Override
					public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
						Jedis jedis = (Jedis) connection.getNativeConnection();

						String result = jedis.set(lockKey, requestValue, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
						if (LOCK_SUCCESS.equals(result)) {
							return true;
						}
						return false;
					}
				});

				if(!flag){
					repeat += 1;
					Thread.sleep(10 * 1000);
				}
			}

		}catch(Exception e){
			logger.error("获取redis锁异常!",e);
			flag = false;
		}

		return flag;
	}




}
