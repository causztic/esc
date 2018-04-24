package problemSet4;

import java.util.Random;

public class Digit extends Grammar {
	public Digit(){
		super("" + new Random().nextInt(10));
	}

	@Override
	public Grammar[][] getRules() {
		// TODO Auto-generated method stub
		return null;
	}
}
