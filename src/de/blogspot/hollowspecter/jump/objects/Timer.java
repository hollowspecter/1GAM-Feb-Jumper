package de.blogspot.hollowspecter.jump.objects;

public class Timer {
	
	 private int finalTime;
     private int currentTime;
     private boolean timeOver;

     public Timer(int timeInMillis){
           this.finalTime = timeInMillis;
           this.timeOver = false;
           this.currentTime = 0;
     }

     public void addTime(int timeInMillis){
           this.currentTime = this.currentTime + timeInMillis;
           if(currentTime >= finalTime){
                 timeOver = true;
           }
     }
    
     public boolean isTimeOver() {
           return timeOver;
     }
    
     public void reset(){
           this.timeOver = false;
           this.currentTime = 0;  
     }
}
