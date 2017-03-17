package tw.edu.ntut.csie.game.state;

import java.util.ArrayList;
import java.util.List;

import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.GameObject;
import tw.edu.ntut.csie.game.PointerEventHandler;
import tw.edu.ntut.csie.game.extend.BitmapButton;

public class Button implements GameObject {
  //private BitmapButton button_attack_normal;
  //private BitmapButton button_attack_pressed;
  private int at_x = 700;
  private int at_y = 300;

  private BitmapButton attack = new BitmapButton(R.drawable.button_attack_normal);
  public Button() {}
/*
  public Button() {
    attack = new BitmapButton(R.drawable.button_attack_normal);
    attack.loadPressedBitmap(R.drawable.button_attack_pressed);
    attack.loadHoveredBitmap(R.drawable.button_attack_hovered);
  }
*/
  public void initialize(){
      attack.loadPressedBitmap(R.drawable.button_attack_pressed);
      attack.loadHoveredBitmap(R.drawable.button_attack_hovered);
      attack.setLocation(at_x,at_y);
  }

@Override
  public void show() {
    attack.show();
  }

@Override
  public void move(){}

  public void release() {
    attack.release();
    attack = null;
  }


  /*public void initialize(){
    at_normalImage.setLocation(at_x,at_y);
    at_pressedImage.setLocation(at_x,at_y);
  }
  @Override
  public void move(){}


  @Override
  public void release(){
    at_normalImage.release();
    at_pressedImage.release();

    at_normalImage = null;
    at_pressedImage = null;
  }

  @Override
  public boolean pointerPressed(List<Pointer> pointers) {
      if (pointers.size() == 1 && at_visible) {
          Pointer pointer = pointers.get(0);
          if (pointer.getType() == Pointer.LEFT_MOUSE_BUTTON && contains(pointer)) {
              at_pressed = true;
              return true;
          }
      }
      return false;
  }

  @Override
  public boolean pointerMoved(List<Pointer> pointers) {
      if (pointers.size() == 1 && at_visible) {
          Pointer pointer = pointers.get(0);
          if (pointer.getType() == Pointer.HOVER) {
              at_hovered = contains(pointer);
          }
      }
      return false;
  }

  @Override
  public boolean pointerReleased(List<Pointer> pointers) {
      at_pressed = false;
      if (pointers.size() == 1 && at_visible) {
          Pointer pointer = pointers.get(0);
          if (pointer.getType() == Pointer.LEFT_MOUSE_BUTTON && contains(pointer)) {
              notifyButtonEventHandlers();
              return true;
          }
      }
      return false;
  }


  @Override
  public void show(){
    if (!at_visible) {
        return;
    }
    if (at_pressed && at_pressedImage != null) {
        at_pressedImage.show();
    } else if (at_normalImage != null) {
        at_normalImage.show();
    }
  }

  private boolean contains(Pointer pointer) {
      return contains(pointer.getX(), pointer.getY());
  }

  private void notifyButtonEventHandlers() {
      for (ButtonEventHandler handler : at_handlers) {
          handler.perform(this);
      }
  }*/

}
