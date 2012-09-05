package com.zenred.cosmos_spring;

import org.springframework.web.context.support.XmlWebApplicationContext;


/**
 * <p>
 * This class is the main accessor to all the beans defined in the Spring contexts.
 * It loads all the contexts in the WLMS classpath that has the name format 
 * <code>spring-*-context.xml</code>, and uses XmlWebApplicationContext so 
 * <code>request</code> scope can be used in the contexts.
 * <p>
 * Also, use this class (via SpringBeans) to conveniently access beans by a simple method call
 * instead of the usual <code>getBean</code> method call (@see SpringBeans). 
 *   
 * @author John Redden taken form Ji Kims model of spring for wlms.
 *
 */
public class SpringContext
{
	/**
	 * Main application context, which automatically finds all context files 
	 * formatted as "spring-*-context.xml" within the classpath.
	 */
	public static final XmlWebApplicationContext app = new XmlWebApplicationContext();
	
	 // Default context configuration locations.
	private static String[] configLocations = new String[] {
		"classpath*:spring-*-context.xml", 
		"classpath*:/com/matrix/cosmos_spring/**/spring-*-context.xml"
    };

	/**
	 * Instance of the SpringBeans which contains convenience methods to
	 * retrieve beans from context.
	 */
	public static ISpringBeans beans;

	// Initialize.
	static
	{
		// Application context.
		load(configLocations);
	    
	    // SpringBeans.
	    beans = (ISpringBeans) app.getBean("springBeans");
	}
	
	/**
	 * Set and load context config locations.
	 * @param configLocations
	 */
	public static void load(String[] configLocations) 
	{
		SpringContext.configLocations = configLocations;
	    app.setConfigLocations(configLocations);
	    app.refresh();
	}
	
	/**
	 * Return the current context config locations. 
	 * The value will change if load() is called.
	 * 
	 * @return String[] Context config locations
	 */
	public static String[] getConfigLocations() {
		return configLocations;
	}
	
}
