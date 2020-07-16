package basic.concurrency.sync;

import java.util.HashSet;
import java.util.Set;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author lijian
 * @description 开放调用防止死锁
 * @date 2020/7/3
 */
public class CooperatingNoDeadlock {
    @ThreadSafe
    class Taxi {
        @GuardedBy("this") private String location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized String getLocation() {
            return location;
        }

        public synchronized void setLocation(String location) {
            boolean reachedDestination;

            // 加Taxi内置锁
            synchronized (this) {
                this.location = location;
                reachedDestination = location.equals(destination);
            }
            // 执行同步代码块后完毕，释放锁



            if (reachedDestination)
                // 加Dispatcher内置锁
                dispatcher.notifyAvailable(this);
        }

        public synchronized String getDestination() {
            return destination;
        }

        public synchronized void setDestination(String destination) {
            this.destination = destination;
        }
    }

    @ThreadSafe
    class Dispatcher {
        @GuardedBy("this") private final Set<Taxi> taxis;
        @GuardedBy("this") private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            taxis = new HashSet<Taxi>();
            availableTaxis = new HashSet<Taxi>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

        public Image getImage() {
            Set<Taxi> copy;

            // Dispatcher内置锁
            synchronized (this) {
                copy = new HashSet<Taxi>(taxis);
            }
            // 执行同步代码块后完毕，释放锁

            Image image = new Image();
            for (Taxi t : copy)
                // 加Taix内置锁
                image.drawMarker(t.getLocation());
            return image;
        }
    }

    class Image {
        public void drawMarker(String p) {
        }
    }

}
