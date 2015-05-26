package com.iplusplus.custopoly.app;

import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import com.iplusplus.custopoly.Custopoly;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fran on 04/05/2015.
 */
public class DiceFragment extends DialogFragment {

    private boolean finish = false;

    public interface DiceDialogListener {
        void onFinishDiceDialog(int diceResult);
    }

    private ImageView dice_picture1;        //reference to dice picture 1
    private ImageView dice_picture2;        //reference to dice picture 2
    private Random rng = new Random();    //generate random numbers
    private SoundPool dice_sound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    private int sound_id;        //Used to control sound stream return by SoundPool
    private Handler handler;    //Post message to start roll
    private Timer timer = new Timer();    //Used to implement feedback to user
    private boolean rolling = false;        //Is die rolling?
    private int die1Value;
    private int die2Value;


    static DiceFragment newInstance(String title) {
        DiceFragment fragment = new DiceFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dice, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        //load dice sound
        sound_id = dice_sound.load(Custopoly.getAppContext(), R.raw.shake_dice, 1);
        //get reference to image widget
        dice_picture1 = (ImageView) v.findViewById(R.id.dice_fragment_iv_dice1);
        dice_picture2 = (ImageView) v.findViewById(R.id.dice_fragment_iv_dice2);
        rolling = false;
        //link handler to callback
        handler = new Handler(callback);

        dice_picture1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            HandleClick();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        dice_picture2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            HandleClick();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        return v;
    }

    //User pressed dice, lets start
    private void HandleClick() throws InterruptedException {
        if(finish)
            sendResult();
        else {
            if (!rolling) {
                rolling = true;
                //Show rolling image
                dice_picture1.setImageResource(R.drawable.dice3droll);
                dice_picture2.setImageResource(R.drawable.dice3droll);
                //Start rolling sound
                dice_sound.play(sound_id, 1.0f, 1.0f, 0, 0, 1.0f);
                //Pause to allow image to update
                timer.schedule(new Roll(), 400);
            }
        }
    }

    private void sendResult() {
        DiceDialogListener activity = (DiceDialogListener) getActivity();
        activity.onFinishDiceDialog(die1Value + die2Value);
        dismiss();
    }

    class Roll extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }

    //Receives message from timer to start dice roll
    protected Handler.Callback callback = new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            //Get roll result
            //Remember nextInt returns 0 to 5 for argument of 6
            //hence + 1
            switch (rng.nextInt(6) + 1) {
                case 1:
                    dice_picture1.setImageResource(R.drawable.one);
                    die1Value = 1;
                    break;
                case 2:
                    dice_picture1.setImageResource(R.drawable.two);
                    die1Value = 2;
                    break;
                case 3:
                    dice_picture1.setImageResource(R.drawable.three);
                    die1Value = 3;
                    break;
                case 4:
                    dice_picture1.setImageResource(R.drawable.four);
                    die1Value = 4;
                    break;
                case 5:
                    dice_picture1.setImageResource(R.drawable.five);
                    die1Value = 5;
                    break;
                case 6:
                    dice_picture1.setImageResource(R.drawable.six);
                    die1Value = 6;
                    break;
                default:
            }

            switch (rng.nextInt(6) + 1) {
                case 1:
                    dice_picture2.setImageResource(R.drawable.one);
                    die2Value = 1;
                    break;
                case 2:
                    dice_picture2.setImageResource(R.drawable.two);
                    die2Value = 2;
                    break;
                case 3:
                    dice_picture2.setImageResource(R.drawable.three);
                    die2Value = 3;
                    break;
                case 4:
                    dice_picture2.setImageResource(R.drawable.four);
                    die2Value = 4;
                    break;
                case 5:
                    dice_picture2.setImageResource(R.drawable.five);
                    die2Value = 5;
                    break;
                case 6:
                    dice_picture2.setImageResource(R.drawable.six);
                    die2Value = 6;
                    break;
                default:
            }
            rolling = false;    //user can press again
            finish = true;
            return true;
        }
    };
}






