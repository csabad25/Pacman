public class MenuControll implements Runnable{
    private Menu m;
    private boolean on = true;
    public MenuControll(Menu m) {
        this.m = m;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    @Override
    public void run() {
        while(on){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            m.repaint();
        }
    }
}
