package tw.edu.ntut.csie.game.Enemy;

import tw.edu.ntut.csie.game.Character.CharacterObject;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.state.Navigation;
import tw.edu.ntut.csie.game.state.Stage1BG;

/**
 * Created by huyuxiang on 2017/4/21.
 */

public class Marin implements EnemyObject {
    private Animation marin;
    private Animation marin_r;
    private Animation marinDead;
    private Animation marinDead_r;
    private Animation marinHit;
    private Animation marinHit_r;
    private int px, py;

    private boolean visible = false, visible_r = true;
    private boolean hitVisible = false, hitVisible_r = false;
    private boolean deadVisible = false, deadVisible_r = false;

    private boolean getHit = false, getHit_r = false;

    private int healthPoint = 100;

    public Marin() {
        marin = new Animation();        marin_r = new Animation();
        marinDead = new Animation();    marinDead_r = new Animation();
        marinHit = new Animation();     marinHit_r = new Animation();

        px = 600; py = 200;
    }

    public void initialize() {
        addFrame();
        setLocation(px, py);
        setDelay();
        setVisible();
        setRepeating();
        setCurrentIndex();
    }

    public int getX() {return px;}
    public int getY() {return py;}
    public int getDamage() {
        return 0;
    }
    public int[] getAttackArea() {
        int[] a = new int[4];
        return a;
    }
    public boolean isAttacking() {
        return false;
    }
    public boolean isAttacking_r() {
        return false;
    }
    public boolean isDead() {
        if (healthPoint <= 0)
            return true;
        return false;
    }

    public void show() {
        marin.show();           marin_r.show();
        marinHit.show();        marinHit_r.show();
        marinDead.show();       marinDead_r.show();

    }

    public void move(CharacterObject ch, int roadPx) {
        marin.move();           marin_r.move();
        marinHit.move();        marinHit_r.move();
        marinDead.move();       marinDead_r.move();

        //To handle if marin is dead
        if (healthPoint <= 0) {
            if (marinHit.isLastFrame())
                deadVisible = true;
            else if(marinHit_r.isLastFrame())
                deadVisible_r = true;

        }
        else {
            getHit(ch);
        }
        //Let marin won't move with luffy
        if (Stage1BG.roadPx < 800 && Stage1BG.roadPx > -800)
            px -= (Navigation.controllerPx - Navigation.initialCtrlPx)/5;
        if (Stage1BG.roadPx > 800)
            px = 800;
        else if (Stage1BG.roadPx < -800)
            px = -800;
        else if ( (Stage1BG.roadPx == 800 && ch.getX() >= 410) ||
                (Stage1BG.roadPx == -800 && ch.getX() <= 390) )
            px -= (Navigation.controllerPx -  Navigation.initialCtrlPx)/5;
        setVisible();
        setLocation(px, py);


    }

    public void release() {
        marin.release();        marin_r.release();
        marinHit.release();     marinHit_r.release();
        marinDead.release();    marinDead_r.release();
    }



    /************************
     * Move Function Area *
     ************************/

    //Set marin's location
    public void setLocation(int x, int y) {
        marin.setLocation(x, y);        marin_r.setLocation(x, y);
        marinHit.setLocation(x, y);     marinHit_r.setLocation(x, y);
        marinDead.setLocation(x, y);    marinDead_r.setLocation(x, y);
    }

    //Sett all marin animations visible
    public void setVisible() {
        marin.setVisible(visible);          marin_r.setVisible(visible_r);
        marinHit.setVisible(hitVisible);    marinHit_r.setVisible(hitVisible_r);
        marinDead.setVisible(deadVisible);        marinDead_r.setVisible(deadVisible_r);
    }

    //To handle the event luffy get hit
    public void getHit(CharacterObject ch) {
        //To determine if marin is getting hit and which side to get hit
        if (ch.isAttacking_r()) {
            if (px <= ch.getAttackArea()[3] && px + marin.getWidth() >= ch.getAttackArea()[0] &&
                    py + marin.getHeight() >= ch.getAttackArea()[1] && py <= ch.getAttackArea()[2] &&
                    !getHit) {
                marinHit.reset();
                visible = false;
                visible_r = false;
                hitVisible = true;
                healthPoint -= ch.getDamage();
                getHit = true;
            }
        } else if (ch.isAttacking()) {
            if (px + marin.getWidth() >= ch.getAttackArea()[0] && px <= ch.getAttackArea()[3] &&
                    py + marin.getHeight() >= ch.getAttackArea()[1] && py <= ch.getAttackArea()[2] &&
                    !getHit_r) {
                marinHit_r.reset();
                visible = false;
                visible_r = false;
                hitVisible_r = true;
                healthPoint -= ch.getDamage();
                getHit_r = true;
            }
        }

        //let marin be hit away
        if (marinHit.getCurrentFrameIndex() >= 0) {
            px--;
        } else if (marinHit_r.getCurrentFrameIndex() >= 0) {
            px++;
        }

        //Handle the event marin finish getting hit
        if (marinHit.getCurrentFrameIndex() == -1 && marinHit_r.getCurrentFrameIndex() == -1) {
            if (getHit_r) {
                visible_r = true;
                hitVisible_r = false;
                getHit_r = false;
            } else if (getHit) {
                visible = true;
                hitVisible = true;
                getHit = false;
            }
        }


    }

    public boolean isInAttackArea(int[] attackArea) {
        if ( px >= attackArea[0] && px <= attackArea[2] &&
                py >= attackArea[1] && py <= attackArea[3])
            return true;
        return false;
    }


    /****************************
     * Initialize Function Area *
     ****************************/

    //Add marin's frames
    public void addFrame() {
        marin.addFrame(R.drawable.marin);
        marin_r.addFrame(R.drawable.marin_r);

        marinHit.addFrame(R.drawable.marin_hit01);
        marinHit.addFrame(R.drawable.marin_hit02);
        marinHit.addFrame(R.drawable.marin_hit03);
        marinHit.addFrame(R.drawable.marin_hit04);
        marinHit.addFrame(R.drawable.marin_hit05);
        marinHit_r.addFrame(R.drawable.marin_hit01_r);
        marinHit_r.addFrame(R.drawable.marin_hit02_r);
        marinHit_r.addFrame(R.drawable.marin_hit03_r);
        marinHit_r.addFrame(R.drawable.marin_hit04_r);
        marinHit_r.addFrame(R.drawable.marin_hit05_r);

        marinDead.addFrame(R.drawable.marin_hit05);
        marinDead_r.addFrame(R.drawable.marin_hit05_r);
    }

    public void setDelay() {
        marin.setDelay(5);
        marin_r.setDelay(5);
        marinDead.setDelay(5);
        marinDead_r.setDelay(5);
        marinHit.setDelay(4);
        marinHit_r.setDelay(4);
    }

    public void setRepeating() {
        marinHit.setRepeating(false);
        marinHit_r.setRepeating(false);
        marinDead.setRepeating(true);
    }

    public void setCurrentIndex() {
        marinHit.setCurrentFrameIndex(-1);
        marinHit_r.setCurrentFrameIndex(-1);
    }
}
