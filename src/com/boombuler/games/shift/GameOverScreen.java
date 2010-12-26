package com.boombuler.games.shift;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGSize;

import android.view.MotionEvent;

import com.boombuler.games.shift.Game.Difficulty;
import com.boombuler.games.shift.render.Background;
import com.boombuler.games.shift.render.Label;

public class GameOverScreen extends CCLayer {

	public static CCScene scene(Difficulty difficulty, long totalscore) {
		boolean NewHighscore = Settings.Current().addToHighscore(difficulty, totalscore);
		
		CCScene result = CCScene.node();
		result.addChild(new Background());
		result.addChild(new GameOverScreen(NewHighscore, totalscore));
		return result;
	}
	
	private GameOverScreen(boolean highscore, long totalscore) {
		this.setIsTouchEnabled(true);
		this.setScale(Main.SCALE);
		String txt = MyResources.string(highscore ? R.string.new_highscore : R.string.you_loose);
		Label lbl = new Label(txt);
		CGSize s = CCDirector.sharedDirector().winSize();
		float yPos = s.height / 2;
		lbl.setPosition(s.width / 2,yPos);
		this.addChild(lbl);
		yPos -= Label.DEFAULT;
		Label score = new Label(String.format(MyResources.string(R.string.new_hs_score), totalscore), Label.SMALLER);
		score.setPosition(s.width / 2, yPos);
		this.addChild(score);
	}
	
	
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {		
		CCDirector.sharedDirector().replaceScene(Main.getTransisionFor(Board.scene()));
		
		return super.ccTouchesEnded(event);
	}
}