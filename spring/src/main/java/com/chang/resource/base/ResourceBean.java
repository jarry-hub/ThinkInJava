//需在配置文件中定义bean

package com.chang.resource.base;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

public class ResourceBean implements ResourceLoaderAware {
	
	private ResourceLoader resourceLoader;
	
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

}
