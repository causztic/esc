package problemSet4;

public class Term extends Grammar{

	@Override
	public Grammar[][] getRules() {
		// TODO Auto-generated method stub
		return new Grammar[][]{ {new Term(), new BaseRule("*"), new Factor()},
			   {new Term(), new BaseRule("/"), new Factor()},
			   {new Factor()}
			 };
	}
}
