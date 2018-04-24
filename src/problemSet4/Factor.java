package problemSet4;

public class Factor extends Grammar {
	@Override
	public Grammar[][] getRules() {
		// TODO Auto-generated method stub
		return 	new Grammar[][] { { new BaseRule("-"), new IntegerRule() },
			{ new BaseRule("("), new Expr(), new BaseRule(")") },
			{ new IntegerRule() },
			{ new IntegerRule(), new BaseRule("."), new IntegerRule() },
			 };
	}
}
