package api.controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/RestServices")
public class MyApp extends Application{
	 public Set<Class<?>> getClasses() {

         return new HashSet<Class<?>>(Arrays.asList(
                     TypesController.class));
   }
}
