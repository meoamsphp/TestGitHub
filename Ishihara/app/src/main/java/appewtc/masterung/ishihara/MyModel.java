package appewtc.masterung.ishihara;

/**
 * Created by masterUNG on 12/28/14 AD.
 */
public class MyModel {

    //Explicit
    private int intButton, intRadio;

    //Create Interface
    public interface OnMyModelChangeListener {
        void onMyModelChangeListener(MyModel myModel);
    }   // interface

    private OnMyModelChangeListener onMyModelChangeListener;

    public void setOnMyModelChangeListener(OnMyModelChangeListener onMyModelChangeListener) {
        this.onMyModelChangeListener = onMyModelChangeListener;
    }

    public int getIntButton() {
        return intButton;
    }   // getter intButton

    public void setIntButton(int intButton) {
        this.intButton = intButton;

        if (this.onMyModelChangeListener != null) {
            this.onMyModelChangeListener.onMyModelChangeListener(this);
        }

    }   // setter intButton

    public int getIntRadio() {
        return intRadio;
    }   // getter intRadio

    public void setIntRadio(int intRadio) {
        this.intRadio = intRadio;

        if (this.onMyModelChangeListener != null) {
            this.onMyModelChangeListener.onMyModelChangeListener(this);
        }

    }   // setter intRadio

}   // Main Class
