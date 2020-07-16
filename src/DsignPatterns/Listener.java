package DsignPatterns;

/**
 * @author lijian
 * @description
 * @date 2020/7/7
 */
public class Listener {
    /**
     * 事件监听器
     * 监听person的 eat（）方法
     */
    interface InterListener{
        void eat(Event event);
    }

    /**
     * 事件源person
     * 要关联监听对象
     */
    static class Person{
        private InterListener interListener;

        void eat(){
            interListener.eat(new Event(this));
        }

        public void getInterListener(InterListener interListener) {
            this.interListener = interListener;
        }
    }

    /**
     * 事件对象
     * 封装了事件源
     */
    static class Event{
        private Person person;
        public Event(){}
        public Event(Person p){
            this.person = p;
        }

        public Person getPerson() {
            return person;
        }
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.getInterListener(new InterListener() {
            @Override
            public void eat(Event event) {
                Person person1 = event.getPerson();
                System.out.println(person1 + "正在吃饭呢");
                person1.eat();
            }
        });
        person.eat();
    }


}
