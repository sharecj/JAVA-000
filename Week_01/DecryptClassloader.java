package Week_01;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DecryptClassloader extends ClassLoader{

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("./Hello.xlass");
            byte[] bytes = new byte[inputStream.available()];
            int length = inputStream.read(bytes);
            for(int i=0; i<length; i++) {
                bytes[i] = (byte)(255 - (int)bytes[i]);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    public static void main(String[] args) {
        try{
            Object hello = new DecryptClassloader().findClass("Hello").newInstance();
            Method method = hello.getClass().getMethod("hello");
            method.invoke(hello);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InstantiationException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
