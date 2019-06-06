import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommonUtil {
    public static String getPort(String port){
        return port;
    }

    private static void checkObjectType(List<? extends Generatic> arrayList){
        if(!CollectionUtils.isEmpty(arrayList)){
            Iterator<?> iterate = arrayList.iterator();
            while(iterate.hasNext()){
                Generatic generatic = (Generatic) iterate.next();
                System.out.println(generatic.getName());

            }

        }
    }

    public static void main(String[] args){
        List list = new ArrayList();
        Generatic g = new Generatic<String>();
        g.setName("11");
        g.setT("dd");
        list.add(g);
        checkObjectType(list);
    }

    static class Generatic<T>{
        private String name;
        private T t;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }

    static class GeneraticChild<T> extends Generatic{
        private String sex;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
