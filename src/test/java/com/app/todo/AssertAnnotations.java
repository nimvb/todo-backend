package com.app.todo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AssertAnnotations {
    private static void assertAnnotations(
            List<Class> annotationClasses, List<Annotation> annotations) {
        // length
        if (annotationClasses.size() != annotations.size()) {
            throw new AssertionError(
                    String.format("Expected %d annotations, but found %d"
                            , annotationClasses.size(), annotations.size()
                    ));
        }
        // exists
        annotationClasses.forEach(
                ac -> {
                    long cnt
                            = annotations.stream()
                            .filter(a -> a.annotationType().isAssignableFrom(ac))
                            .count();
                    if (cnt == 0) {
                        throw new AssertionError(
                                String.format("No annotation of type %s found", ac.getName())
                        );
                    }
                }
        );
    }
    public static void assertType(Class c, Class... annotationClasses) {
        assertAnnotations(
                Arrays.asList(annotationClasses)
                , Arrays.asList(c.getAnnotations())
        );
    }
    public static void assertField(
            Class c, String fieldName, Class... annotationClasses) {
        try {
            assertAnnotations(
                    Arrays.asList(annotationClasses)
                    , Arrays.asList(c.getDeclaredField(fieldName).getAnnotations())
            );
        } catch (NoSuchFieldException nsfe) {
            throw new AssertionError(nsfe);
        }
    }
    public static void assertMethod(
            Class c, String getterName, Class...annotationClasses) {
        try {
            assertAnnotations(
                    Arrays.asList(annotationClasses)
                    , Arrays.asList(c.getDeclaredMethod(getterName).getAnnotations())
            );
        } catch (NoSuchMethodException nsfe) {
            throw new AssertionError(nsfe);
        }
    }

    public static void assertMethodExist(Class c, String... names){
        try{
            for (String name :
                    names) {
                long count = Arrays.asList(c.getDeclaredMethods()).stream().filter(m -> m.getName().equals(name)).count();
                if(count == 0)
                    throw new NoSuchMethodException("Method " + name + " dose not exist");
                //c.getDeclaredMethod(names[0]);
            }

        }catch (NoSuchMethodException | NullPointerException ex){
            throw new AssertionError(ex);
        }
    }

    public static void assertGetters(Class c, String... names) throws AssertionError {

        try {
            for (String name : names) {
                String capName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                boolean logical = c.getDeclaredField(name).getType().equals(boolean.class);
                if(logical){
                    assertMethodExist(c, "is" + capName);

                }else {
                    assertMethodExist(c, "get" + capName);
                }

            }

        } catch (AssertionError | NoSuchFieldException ex) {
            throw new AssertionError(ex);
        }

    }


    public static void assertSetters(Class c, String... names) throws AssertionError {

        try {
            for (String name : names) {
                String capName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                assertMethodExist(c, "set" + capName);
            }

        } catch (AssertionError ex) {
            throw new AssertionError(ex);
        }

    }

    public static void assertGettersSetters(Class c, String... names) throws AssertionError {

        try {
            assertGetters(c,names);
            assertSetters(c,names);

        } catch (AssertionError ex) {
            throw new AssertionError(ex);
        }

    }
}
