package problemSet4;

public class Expr extends Grammar{
	@Override
	public Grammar[][] getRules() {
		// TODO Auto-generated method stub
		return new Grammar[][]  { {new Expr(), new BaseRule("+"), new Term()},
			   {new Expr(), new BaseRule("-"), new Term()},
			   {new Term()}
			 };
	}
}
