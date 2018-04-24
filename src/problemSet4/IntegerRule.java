package problemSet4;

public class IntegerRule extends Grammar {

	@Override
	public Grammar[][] getRules() {
		// TODO Auto-generated method stub
		return new Grammar[][] { { new Digit() },
				{ new Digit(), new IntegerRule() },
		 };
	}
}
