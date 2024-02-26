public class Timer implements java.lang.Runnable{

    @Override
    public void run() {
        this.runTimer();
    }

    public void runTimer(){
        int i = 5;
         while (i>0){
          System.out.println("Remaining: "+i+" seconds");
          try {
            i--;
            Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
           }
           catch (InterruptedException e) {
               //I don't think you need to do anything for your particular problem
           }
         }
    }

    public static void main(String[] args){
        Timer t = new Timer();
        t.run();
    }
}