import acm.graphics.*;
import acm.util.ErrorException;

import java.awt.event.HierarchyBoundsAdapter;
import java.awt.font.GlyphJustificationInfo;
import java.beans.beancontext.BeanContext;

public class HangmanCanvas extends GCanvas{
    /* Reset the display so that only the scaffold appears */
    public void reset(){
        showScaffold();
    }

    private void showScaffold(){
        removeAll();
        double scfldTopX = getWidth()/2 - BEAM_LENGTH;
        double scfldTopY = getHeight()/2 - BODY_LENGTH - HEAD_RADIUS * 2 - ROPE_LENGTH;
        GLine scfld = new GLine(scfldTopX, scfldTopY + SCAFFOLD_HEIGHT, scfldTopX, scfldTopY);
        GLine beam = new GLine(scfldTopX, scfldTopY, scfldTopX + BEAM_LENGTH, scfldTopY);
        GLine rope = new GLine(scfldTopX + BEAM_LENGTH, scfldTopY,
                scfldTopX + BEAM_LENGTH, scfldTopY + ROPE_LENGTH);
        add(scfld);
        add(beam);
        add(rope);
    }

    /**
     * Updates the word on the screen to correspond to the current state of the game. The argument string shows
     * what letters have been guessed so far; unguessed letters are indicated by hyphens.
     */

    public void displayWord(String word){
        double posX = getWidth() / 2 - BEAM_LENGTH;
        double posY = getHeight() * 5 / 6;
        GLabel showingProgress = new GLabel(word, posX, posY);
        showingProgress.setFont("Halvetica-25");
        if (getElementAt(posX, posY) != null){
            remove(getElementAt(posX, posY));
        }
        add(showingProgress);

    }

    /**
     * Update the display to correspond to an incorrect guess by the user.
     * Calling this method causes the next body part to appear on the scaffold and adds the letter to the list of
     * incorrect guesses that appears at the bottom of the window.
     */
    public void noteIncorrectGuess(String wrongGuess){
        if (wrongGuess.length() == 1){
            showHead();
        }else if (wrongGuess.length() == 2){
            showBody();
        }else if (wrongGuess.length() == 3){
            showLeftHand();
        }else if (wrongGuess.length() == 4){
            showRightHand();
        }else if (wrongGuess.length() == 5){
            showLeftLeg();
        }else if (wrongGuess.length() == 6){
            showRightLeg();
        }else if (wrongGuess.length() == 7){
            showLeftFoot();
        }else if (wrongGuess.length() == 8){
            showRightFoot();
        }else{
            throw new ErrorException("Error!");
        }
        GLabel incorrectGuess = new GLabel(wrongGuess, getWidth()/2 - BEAM_LENGTH, getHeight() * 9 /10);
        incorrectGuess.setFont("Halvetica-20");
        add(incorrectGuess);
    }

    private void showHead(){
        double headX = getWidth() / 2 - HEAD_RADIUS;
        double headCenterY = getHeight() / 2 - BODY_LENGTH - 2 * HEAD_RADIUS;
        GOval head = new GOval(headX, headCenterY, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
        add(head);
    }

    private void showBody(){
        double bodyStartX = getWidth() / 2;
        double bodyStartY = getHeight() / 2;
        GLine body = new GLine(bodyStartX, bodyStartY, bodyStartX, bodyStartY - BODY_LENGTH);
        add(body);
    }

    private void showLeftHand(){
        double upperArmStX = getWidth() / 2;
        double upperArmStY = getHeight() / 2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
        double middleX = getWidth() / 2 - UPPER_ARM_LENGTH;
        double lowerEndY = upperArmStY + LOWER_ARM_LENGTH;
        GLine upperLeftArm = new GLine(upperArmStX, upperArmStY, middleX, upperArmStY);
        GLine lowerLeftArm = new GLine(middleX, upperArmStY, middleX, lowerEndY);
        add(upperLeftArm);
        add(lowerLeftArm);
    }

    private void showRightHand(){
        double upperArmStX = getWidth() / 2;
        double upperArmStY = getHeight() / 2 - BODY_LENGTH + ARM_OFFSET_FROM_HEAD;
        GLine upperRightArm = new GLine(upperArmStX, upperArmStY, upperArmStX + UPPER_ARM_LENGTH, upperArmStY);
        GLine lowerRightArm = new GLine(upperArmStX + UPPER_ARM_LENGTH, upperArmStY,
                upperArmStX + UPPER_ARM_LENGTH, upperArmStY + LOWER_ARM_LENGTH);
        add(upperRightArm);
        add(lowerRightArm);
    }

    private void showLeftLeg(){
        double startX = getWidth() / 2;
        double startY = getHeight() / 2;
        GLine leftHip = new GLine(startX, startY, startX - HIP_WIDTH, startY);
        GLine leftLeg = new GLine(startX - HIP_WIDTH, startY,
                startX - HIP_WIDTH, startY + LEG_LENGTH);
        add(leftHip);
        add(leftLeg);
;    }

    private void showRightLeg(){
        double startX = getWidth() / 2;
        double startY = getHeight() / 2;
        GLine rightHip = new GLine(startX, startY, startX + HIP_WIDTH, startY);
        GLine rightLeg = new GLine(startX + HIP_WIDTH, startY,
                startX + HIP_WIDTH, startY + LEG_LENGTH);
        add(rightHip);
        add(rightLeg);
    }

    private void showLeftFoot(){
        double startX = getWidth() / 2 - HIP_WIDTH;
        double startY = getHeight() / 2 + LEG_LENGTH;
        GLine leftFoot = new GLine(startX, startY, startX - FOOT_LENGTH, startY);
        add(leftFoot);
    }

    private void showRightFoot(){
        double startX = getWidth() / 2 + HIP_WIDTH;
        double startY = getHeight() / 2 + LEG_LENGTH;
        GLine rightFoot = new GLine(startX, startY, startX + FOOT_LENGTH, startY);
        add(rightFoot);
    }

    /* Constants for the simple version of the picture (in pixels) */
    private static final int SCAFFOLD_HEIGHT = 360;
    private static final int BEAM_LENGTH = 144;
    private static final int ROPE_LENGTH = 18;
    private static final int HEAD_RADIUS = 36;
    private static final int BODY_LENGTH = 144;
    private static final int ARM_OFFSET_FROM_HEAD = 28;
    private static final int UPPER_ARM_LENGTH = 72;
    private static final int LOWER_ARM_LENGTH = 44;
    private static final int HIP_WIDTH = 36;
    private static final int LEG_LENGTH = 108;
    private static final int FOOT_LENGTH = 28;
}
