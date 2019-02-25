package com.duobei.console.web.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.duobei.core.auth.domain.credential.OperatorCredential;

public class AuthorizationTag extends TagSupport {
	private static final long serialVersionUID = 8419598654703684473L;
	private String uri;//资源地址
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public int doStartTag() throws JspException {
		//Tag.SKIP_BODY：表示?>…之间的内容被忽略；
		//Tag.EVAL_BODY_INCLUDE：表示标签之间的内容被正常执行。
		HttpSession session=this.pageContext.getSession();
		if(session==null){
			return Tag.SKIP_BODY;
		}else{
			OperatorCredential credential = (OperatorCredential) session.getAttribute(OperatorCredential.Credential_Key);
			if(credential==null){
				return Tag.SKIP_BODY;
			}else{
				if (credential.isSuperAdmin()) {
					return Tag.EVAL_BODY_INCLUDE;
				}
				if (StringUtils.isBlank(uri)) {
					return Tag.SKIP_BODY;
				}
//				String[] uris={uri};
//				if (uri.indexOf(",")!=-1){
//					uris=uri.split(",");
//				}
//				for (String str:uris) {
//					if(credential.getUserResource().contains(str)){
//						return Tag.EVAL_BODY_INCLUDE;
//					}else{
//						AuthService authService=AppContext.getBean(AuthService.class);
//						if (authService.isCommonResourceForLogin(str)) {
//							return Tag.EVAL_BODY_INCLUDE;
//						}
//					}
//				}
				
				return Tag.SKIP_BODY;
			}
		}
	}
}
