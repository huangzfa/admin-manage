(function($, window, document){
	var defaults = {
			mode: '',//image图片,audio音频,video视频
			imageManagerUrl: '',
			imageManagerPageSize:20,
			uploadUrl: '',
			width: '100%',
			height: '400px',
			confirmSelect:null
	    };
	function ResStore(element, options) {
		this.$el = $(element);
		this.resBoxId = 'hjn_rb_'+this.$el.attr('id');
		options.resBoxId=this.resBoxId;
	    this.options = $.extend({}, defaults, options);
	    this.initUI();
	}
	$.fn.resStore = function(options,value) {
		var _this=this.get(0);
		var data = $.data(_this, 'resStore');
	    if (!data) {
	    	data = new ResStore(_this, options);
	    	$.data(_this, 'resStore', data);
	    }
	    if (options === 'test') {
	        data[options](value);
	    }
		return data;
	};
	$.fn.getResStoreObj=function(){
		return $.data(this.get(0), 'resStore');
	}
	ResStore.prototype = {
		initUI:function(){
			var _this=this,_$resBoxId$=_this.resBoxId;
			if(!$('#'+_$resBoxId$).get(0)){
				var content='';
				content+='<div id="'+_$resBoxId$+'" class="h_fs_box">';
				content+='	<div class="h_fs_opt">';
				content+='		<ul class="tab">';
				if('image'==_this.options.mode){
				content+='			<li data-panel-id="imageList'+_$resBoxId$+'">图片</li>';
				}
				if('audio'==_this.options.mode){
				content+='			<li data-panel-id="audioList'+_$resBoxId$+'">音频</li>';
				}
				if('video'==_this.options.mode){
				content+='			<li data-panel-id="videoList'+_$resBoxId$+'">视频</li>';
				}
				content+='			<li data-panel-id="upload'+_$resBoxId$+'">上传</li>';
				content+='		</ul>';
				content+='		<div class="btn_box"><a href="javascript:void(0);" id="hjn_btn_selected'+_$resBoxId$+'" class="h_btn">确认选择</a><a href="javascript:void(0);" id="hjn_btn_close'+_$resBoxId$+'" class="h_btn">取消</a></div>';
				content+='	</div>';
				content+='	<div class="h_fs_content">';
				content+='	    <div id="imageList'+_$resBoxId$+'" class="imageList panel"></div>';
				
				content+='		<div id="upload'+_$resBoxId$+'" class="panel upload">';
				content+='			<div id="queueList'+_$resBoxId$+'" class="queueList">';
				content+='				<div class="statusBar element-invisible">';
				content+='					<div class="progress">';
				content+='						<span class="text">0%</span>';
				content+='						<span class="percentage"></span>';
				content+='					</div>';
				content+='					<div class="info"></div>';
				content+='					<div class="btns">';
				content+='						<div id="filePickerBtn'+_$resBoxId$+'" class="filePickerBtn"></div>';
				content+='						<div class="uploadBtn"></div>';
				content+='					</div>';
				content+='				</div>';
				content+='				<div id="dndArea'+_$resBoxId$+'" class="placeholder">';
				content+='					<div class="filePickerContainer">';
				content+='						<div id="filePickerReady'+_$resBoxId$+'"></div>';
				content+='					</div>';
				content+='				</div>';
				content+='				<ul class="filelist element-invisible">';
				content+='				</ul>';
				content+='			</div>';
				content+='		</div>';
				content+='	</div>';
				content+='</div>';
				$('body').append(content);
			}
			
			this.$el.click(function(){
				_this.layer_index=layer.open({
				  type: 1,
				  title: '资源库',
				  area: ['650px', '450px'], //宽高
				  shade: 0.1,
				  closeBtn: 1, //0隐藏关闭
				  end: function(){
					  
				  },
				  content: $('#'+_$resBoxId$)
				});
				
				$('#'+_$resBoxId$+' .h_fs_opt .tab li:first').click();
		    });
			//取消
			$('#hjn_btn_close'+_$resBoxId$).click(function(){
				layer.close(_this.layer_index);
			});
			//确认选择
			$('#hjn_btn_selected'+_$resBoxId$).click(function(){
				if(imageManager.getSelectedList().length==0){
					layer.close(_this.layer_index);
					return;
				}
				if('image'==_this.options.mode){
                	if(imageManager){
                		_this.options.confirmSelect(imageManager.getSelectedList()[0].url);
                	}
                }
				layer.close(_this.layer_index);
			});
			
			$('#'+_$resBoxId$+' .h_fs_opt .tab li').click(function(){
				if(!$(this).hasClass('selected')){
					$(this).siblings().removeClass('selected');
					$(this).addClass('selected');
				}
				setTabFocus($(this).attr('data-panel-id'),_this.options);
			});
			
			var uploadImage,//上传组件
			imageManager;//图片管理
			function setTabFocus(id,options) {
				$('#'+_$resBoxId$+' .h_fs_content').children('.panel').removeClass('selected');
				$('#'+id).addClass('selected');
				if('upload'+_$resBoxId$==id){
					if(!options.uploadSuccess){
						options.uploadSuccess=function(url){
							if(imageManager){
	                    		imageManager.createImgItem(url,true);
	                    	}
						}
					}
					uploadImage = uploadImage || new UploadImage('queueList'+_$resBoxId$,options);
			        var count = uploadImage.getQueueCount();
			        if (count>0) {
			            $('.info', '#queueList'+_$resBoxId$).html('<span style="color:red;">' + '还有'+count+'个未上传文件' + '</span>');
			        }else{
			        	uploadImage.resetFileQueue();
			        }
				}else if('imageList'+_$resBoxId$==id){
					imageManager = imageManager || new ImageManager(id,options);
					imageManager.cancelSelected();
				}
			}
		}
	}
	
	
	/* 上传图片 */
	function UploadImage(id,options) {
		console.log('新建UploadImage');
		var defaults = {
				uploadSuccess:null
		    };
	    this.$wrap = $('#' + id);
	    this.options = $.extend({}, defaults, options);
	    this.uploadUrl=options.uploadUrl;
	    this.acceptExtensions = options.acceptExtensions;
	    this.fileMaxSize = options.fileMaxSize;
	    this.init();
	}
	UploadImage.prototype = {
	    init: function () {
	        this.initContainer();
	        this.initUploader();
	    },
	    initContainer: function () {
	        this.$queue = this.$wrap.find('.filelist');
	    },
	    /* 初始化容器 */
	    initUploader: function () {
	        var _this = this,
	        	_$resBoxId$=_this.options.resBoxId,
	            $wrap = _this.$wrap,
	        // 图片容器
	            $queue = $wrap.find('.filelist'),
	        // 状态栏，包括进度和控制按钮
	            $statusBar = $wrap.find('.statusBar'),
	        // 文件总体选择信息。
	            $info = $statusBar.find('.info'),
	        // 上传按钮
	            $upload = $wrap.find('.uploadBtn'),
	        // 没选择文件之前的内容。
	            $placeHolder = $wrap.find('.placeholder'),
	        // 总体进度条
	            $progress = $statusBar.find('.progress').hide(),
	        // 添加的文件数量
	            fileCount = 0,
	        // 添加的文件总大小
	            fileSize = 0,
	        // 优化retina, 在retina下这个值是2
	            ratio = window.devicePixelRatio || 1,
	        // 缩略图大小
	            thumbnailWidth = 110 * ratio,
	            thumbnailHeight = 110 * ratio,
	        // 可能有pedding, ready, uploading, confirm, done.
	            state = '',
	        // 所有文件的进度信息，key为file id
	            percentages = {},
	        // WebUploader实例
	            uploader;
	        if (!WebUploader.Uploader.support()) {
	            $('#filePickerReady'+_$resBoxId$).after($('<div>').html('WebUploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器。')).hide();
	            return;
	        }
	        uploader = _this.uploader = WebUploader.create({
	            pick: {
	                id: '#filePickerReady'+_$resBoxId$,
	                label: '点击选择文件',
	                multiple:false
	            },
	            accept: {
	                title: 'Images',
	                extensions: _this.acceptExtensions,
	                mimeTypes: getMimeTypes(_this.acceptExtensions)
	            },
	            swf: '/static/plugin/fileManager/webuploader/Uploader.swf',
	            server: _this.uploadUrl,
	            fileVal: 'upfile',
	            duplicate: true,
	            fileSingleSizeLimit: _this.fileMaxSize,    // 默认2M 单位字节
	            compress: false
	        });
	        uploader.addButton({
	            id: '#filePickerBtn'+_$resBoxId$,
	            label: '继续添加'
	        });
	        //根据图片类型获取MimeTypes
			function getMimeTypes(acceptExtensions){
				var str="";
				$.each(acceptExtensions.split(','), function (i, v) {
					str+=",image/"+v;
	            });
				return str.replace(/^[,]/, '');
			}
			
	        setState('pedding');
	        
	        // 当有文件添加进来时执行，负责view的创建
	        function addFile(file) {
	            var $li = $('<li id="' + file.id + '">' +
	                    '<p class="title">' + file.name + '</p>' +
	                    '<p class="imgWrap"></p>' +
	                    '<p class="progress"><span></span></p>' +
	                    '</li>'),

	                $btns = $('<div class="file-panel"><span class="cancel">删除</span></div>').appendTo($li),
	                $prgress = $li.find('p.progress span'),
	                $wrap = $li.find('p.imgWrap'),
	                $info = $('<p class="error"></p>').hide().appendTo($li),

	                showError = function (code) {
	                    switch (code) {
	                        case 'exceed_size':
	                            text = '文件大小超出';
	                            break;
	                        case 'interrupt':
	                            text = '文件传输中断';
	                            break;
	                        case 'http':
	                            text = 'http请求错误';
	                            break;
	                        case 'not_allow_type':
	                            text = '文件格式不允许';
	                            break;
	                        default:
	                            text = '上传失败，请重试';
	                            break;
	                    }
	                    $info.text(text).show();
	                };

	            if (file.getStatus() === 'invalid') {
	                showError(file.statusText);
	            } else {
	                $wrap.text('预览中');
	                uploader.makeThumb(file, function (error, src) {
	                    if (error || !src) {
	                        $wrap.text('不能预览');
	                    } else {
	                        var $img = $('<img src="' + src + '">');
	                        $wrap.empty().append($img);
	                        $img.on('error', function () {
	                            $wrap.text('不能预览');
	                        });
	                    }
	                }, thumbnailWidth, thumbnailHeight);
	                percentages[ file.id ] = [ file.size, 0 ];
	                file.rotation = 0;

	                /* 检查文件格式 */
	                if (!file.ext || _this.acceptExtensions.indexOf(file.ext.toLowerCase()) == -1) {
	                    showError('not_allow_type');
	                    uploader.removeFile(file);
	                }
	            }

	            file.on('statuschange', function (cur, prev) {
	            	/*
            		inited 初始状态
					queued 已经进入队列, 等待上传
					progress 上传中
					complete 上传完成。
					error 上传出错，可重试
					interrupt 上传中断，可续传。
					invalid 文件不合格，不能重试上传。会自动从队列中移除。
					cancelled 文件被移除。
	            	 */
	                if (prev === 'progress') {
	                    $prgress.hide().width(0);
	                } else if (prev === 'queued') {
	                    $li.off('mouseenter mouseleave');
	                    $btns.remove();
	                }
	                // 成功
	                if (cur === 'error' || cur === 'invalid') {
	                    showError(file.statusText);
	                    percentages[ file.id ][ 1 ] = 1;
	                } else if (cur === 'interrupt') {
	                    showError('interrupt');
	                } else if (cur === 'queued') {
	                    percentages[ file.id ][ 1 ] = 0;
	                } else if (cur === 'progress') {
	                    $info.hide();
	                    $prgress.css('display', 'block');
	                } else if (cur === 'complete') {
	                }

	                $li.removeClass('state-' + prev).addClass('state-' + cur);
	            });

	            $li.on('mouseenter', function () {
	                $btns.stop().animate({height: 30});
	            });
	            $li.on('mouseleave', function () {
	                $btns.stop().animate({height: 0});
	            });
	            $li.click(function(){
	            	var _this=$(this);
	            	if(_this.hasClass('state-complete')){
	            		_this.siblings().removeClass('selected');
	            		if(!_this.hasClass('selected')){
	            			_this.addClass('selected');
	                	}
	            	}
	            });

	            $btns.on('click', '.cancel', function () {
	            	uploader.removeFile(file);
	            });
	            $queue.append($li);
	        }

	        // 负责view的销毁
	        function removeFile(file) {
	            var $li = $('#' + file.id);
	            delete percentages[ file.id ];
	            updateTotalProgress();
	            $li.off().find('.file-panel').off().end().remove();
	        }

	        function updateTotalProgress() {
	            var loaded = 0,
	                total = 0,
	                spans = $progress.children(),
	                percent;

	            $.each(percentages, function (k, v) {
	                total += v[ 0 ];
	                loaded += v[ 0 ] * v[ 1 ];
	            });

	            percent = total ? loaded / total : 0;
	            spans.eq(0).text(Math.round(percent * 100) + '%');
	            spans.eq(1).css('width', Math.round(percent * 100) + '%');
	            updateStatus();
	        }

	        function setState(val, files) {
	            if (val != state) {
	                var stats = uploader.getStats();
	                $upload.removeClass('state-' + state);
	                $upload.addClass('state-' + val);
	                switch (val) {
	                    /* 未选择文件 */
	                    case 'pedding':
	                        $queue.addClass('element-invisible');
	                        $statusBar.addClass('element-invisible');
	                        $placeHolder.removeClass('element-invisible');
	                        $progress.hide(); 
	                        $info.hide();
	                        uploader.refresh();
	                        break;

	                    /* 可以开始上传 */
	                    case 'ready':
	                        $placeHolder.addClass('element-invisible');
	                        $queue.removeClass('element-invisible');
	                        $statusBar.removeClass('element-invisible');
	                        $progress.hide(); 
	                        $info.show();
	                        $upload.text('开始上传');
	                        uploader.refresh();
	                        break;

	                    /* 上传中 */
	                    case 'uploading':
	                        $progress.show(); 
	                        $info.hide();
	                        $upload.text('暂停上传');
	                        break;

	                    /* 暂停上传 */
	                    case 'paused':
	                        $progress.show(); 
	                        $info.hide();
	                        $upload.text('继续上传');
	                        break;

	                    case 'confirm':
	                        $progress.show(); 
	                        $info.hide();
	                        $upload.text('开始上传');
	                        stats = uploader.getStats();
	                        if (stats.successNum && !stats.uploadFailNum) {
	                            setState('finish');
	                            return;
	                        }
	                        break;

	                    case 'finish':
	                        $progress.hide();
	                        $info.show();
	                        if (stats.uploadFailNum) {
	                            $upload.text('重试上传');
	                        } else {
	                            $upload.text('开始上传');
	                        }
	                        break;
	                }
	                state = val;
	                updateStatus();
	            }
	            if (!_this.getQueueCount()) {
	                $upload.addClass('disabled');
	            } else {
	                $upload.removeClass('disabled');
	            }
	        }

	        function updateStatus() {
	            var text = '', stats;
	            if (state === 'ready') {
	                text = '选中'+fileCount+'个文件，共'+WebUploader.formatSize(fileSize);
	            } else if (state === 'confirm') {
	                stats = uploader.getStats();
	                if (stats.uploadFailNum) {
	                    text = '已成功上传'+stats.successNum+'个文件，'+stats.uploadFailNum+'个文件上传失败';
	                }
	            } else {
	                stats = uploader.getStats();
	                text = '共'+fileCount+'个文件（'+WebUploader.formatSize(fileSize)+'）';
	            }
	            $info.html(text);
	        }
	        //当文件被加入队列以后触发。
	        uploader.on('fileQueued', function (file) {
	            fileCount++;
	            fileSize += file.size;
	            if (fileCount === 1) {
	                $placeHolder.addClass('element-invisible');
	                $statusBar.show();
	            }
	            addFile(file);
	        });
	        //当一批文件添加进队列以后触发。
	        uploader.on('filesQueued', function (files) {
	            if (!uploader.isInProgress() && (state == 'pedding' || state == 'finish' || state == 'confirm' || state == 'ready')) {
	                setState('ready');
	            }
	            updateTotalProgress();
	        });
	        //当文件被移除队列后触发。
	        uploader.on('fileDequeued', function (file) {
	            fileCount--;
	            fileSize -= file.size;
	            removeFile(file);
	            updateTotalProgress();
	        });
	        //所有事件
	        uploader.on('all', function (type, files) {
	            switch (type) {
	            	//当所有文件上传结束时触发，
	                case 'uploadFinished':
	                    setState('confirm', files);
	                    break;
	                //当开始上传流程时触发。
	                case 'startUpload':
	                    setState('uploading', files);
	                    break;
	                //当开始上传流程暂停时触发
	                case 'stopUpload':
	                    setState('paused', files);
	                    break;
	            }
	        });
	        //当某个文件的分块在发送前触发，主要用来询问是否要添加附带参数，大文件在开起分片上传的前提下此事件可能会触发多次
	        uploader.on('uploadBeforeSend', function (file, data, header) {
	            //这里可以通过data对象添加POST参数
	            header['X_Requested_With'] = 'XMLHttpRequest';
	        });
	        //上传过程中触发，携带上传进度
	        uploader.on('uploadProgress', function (file, percentage) {
	            var $li = $('#' + file.id),
	                $percent = $li.find('.progress span');

	            $percent.css('width', percentage * 100 + '%');
	            percentages[ file.id ][ 1 ] = percentage;
	            updateTotalProgress();
	        });
	        //当文件上传成功时触发,每个文件触发
	        uploader.on('uploadSuccess', function (file, data) {
	            var $file = $('#' + file.id);
	            try {
	                if (data.code) {
	                	$file.attr('_url',data.url);
	                    $file.append('<span class="success"></span>');
	                    if('image'==_this.options.mode){
	                    	if(_this.options.uploadSuccess){
	                    		_this.options.uploadSuccess(data.url);
	                    	}
	                    }
	                } else {
	                    $file.find('.error').text(data.msg).show();
	                }
	                setTimeout(function(){
                    	uploader.removeFile(file);
                    },3000);
	            } catch (e) {
	                $file.find('.error').text('服务器返回出错'+e).show();
	            }
	        });
	        //当文件上传出错时触发
	        uploader.on('uploadError', function (file, code) {
	        });
	        //当validate不通过时触发
	        uploader.on('error', function (code, file) {
	        	if(code=='Q_EXCEED_NUM_LIMIT'){
	        		layer.msg('超出文件总数量');
	        	}else if (code == 'Q_TYPE_DENIED') {
	        		layer.msg('文件类型错误');
	            }else if(code=='Q_EXCEED_SIZE_LIMIT'){
	            	layer.msg('超出文件总大小');
	        	}else if(code=='F_EXCEED_SIZE'){
	            	layer.msg('超出文件大小');
	        	}
	        });
	        //不管成功或者失败，文件上传完成时触发,每个文件触发
	        uploader.on('uploadComplete', function (file) {
	        });

	        $upload.on('click', function () {
	            if ($(this).hasClass('disabled')) {
	                return false;
	            }
	            if (state === 'ready') {
	                uploader.upload();
	            } else if (state === 'paused') {
	                uploader.upload();
	            } else if (state === 'uploading') {
	                uploader.stop();
	            }
	        });

	        $upload.addClass('state-' + state);
	        updateTotalProgress();
	    },
	    getQueueCount: function () {
	        var file, i, status, readyFile = 0, files = this.uploader.getFiles();
	        for (i = 0; file = files[i++]; ) {
	            status = file.getStatus();
	            if (status == 'queued' || status == 'uploading' || status == 'progress' || status == 'inited'){
	            	readyFile++;
	            }
	        }
	        return readyFile;
	    },
	    resetFileQueue:function(){
	    	this.uploader.reset();
	    }
	};
	/* 在线图片 */
	function ImageManager(id,options) {
		console.log('新建ImageManager');
		this.id = id;
	    this.container = $('#' + id);
	    this.imageManagerUrl = options.imageManagerUrl;
	    this.init();
	}
	ImageManager.prototype = {
	    init: function () {
	    	this.initContainer();
	        this.initData();
	        this.initEvents();
	    },
	    /* 初始化容器 */
	    initContainer: function () {
	        this.container.html('');
	        this.list = $("<ul/>");
	        this.container.append(this.list);
	    },
	    /* 初始化第一次的数据 */
	    initData: function () {
	        /* 拉取数据需要使用的值 */
	        this.page = 1;
	        this.isEnd = false;

	        /* 第一次拉取数据 */
	        this.getImageData();
	    },
	    /* 初始化滚动事件,滚动到地步自动拉取数据 */
	    initEvents: function () {
	        var _this = this;
	        /* 滚动拉取图片 */
	        this.container.scroll(function() {
	        	var panel = this;
	            if (panel.scrollHeight - (panel.offsetHeight + panel.scrollTop) < 10) {
	                _this.getImageData();
	            }
			});
	    },
	    /* 向后台拉取图片列表数据 */
	    getImageData: function () {
	        var _this = this;
	        if(!_this.isEnd && !this.isLoadingData) {
	            this.isLoadingData = true;
	            $.ajax({  
			        type:'post',      
			        timeout:5000,
			        url:_this.imageManagerUrl,  
			        data:{page: this.page},
			        dataType:'json',  
			        success:function(data){
			        	try {
	                        if (data.code) {
	                            _this.pushData(data.list);
	                            _this.page++;
	                            _this.isEnd = data.end;
	                            _this.isLoadingData = false;
	                        }
	                    } catch (e) {
	                    	 _this.page = 0;
	                         _this.isEnd = true;
	                         _this.isLoadingData = false;
	                    }
			        },
			        error:function(XMLHttpRequest, textStatus, errorThrown) {
			        	_this.isLoadingData = false;
			        }
			    });
	        }
	    },
	    /* 添加图片到列表界面上 */
	    pushData: function (list) {
	        var i, item, img, icon, _this = this;
	        $.each(list, function(i, file){
	        	if(file && file.url) {
	        		_this.createImgItem(file.url);
	            }
	        });
	    },
	    createImgItem:function(imgUrl,isPre){
	    	_this = this;
	    	if(imgUrl){
	    		item = $('<li/>');
	            img = $('<img/>');
	            icon = $('<span/>');
	            img.load(function(){
	            	_this.scale($(this), $(this).parent().innerWidth(), $(this).parent().innerHeight());
	            });
	            img.attr('src', imgUrl + (imgUrl.indexOf('?') == -1 ? '?noCache=':'&noCache=') + (+new Date()).toString(36) );
	            item.attr('_url', imgUrl);
	            icon.addClass('icon');

	            item.append(img);
	            item.append(icon);
	            if(isPre){
	            	_this.list.prepend(item);
	            }else{
	            	_this.list.append(item);
	            }
	            /* 选中图片 */
	            item.click(function () {
	            	$(this).siblings().removeClass('selected');
	            	if (!$(this).hasClass('selected')) {
	            		$(this).addClass('selected');
		            }
	    	    });
	    	}
	    },
	    /* 改变图片大小 */
	    scale: function (img, w, h, type) {
	        var ow = img.width(),
	            oh = img.height();
	        if (ow >= oh) {
                img.width(w * ow / oh);
                img.height(h);
                img.css('margin-left','-' + parseInt((img.width() - w) / 2) + 'px');
            } else {
                img.width(w);
                img.height(h * oh / ow);
                img.css('margin-top','-' + parseInt((img.height() - h) / 2) + 'px');
            }
	    },
	    cancelSelected: function(){
	    	this.list.children('li').removeClass('selected');
	    },
	    getSelectedList: function () {
	        var i, lis = this.list.children('li'), list = [];
	        $.each(lis, function(i, li){
	        	if ($(li).hasClass('selected')) {
	                list.push({
	                    url: $(li).attr('_url')
	                });
	            }
	        });
	        return list;
	    }
	};
})(jQuery, window, document);