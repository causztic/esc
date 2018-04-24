package problemSet4;

public class S extends Grammar{

	@Override
	public Grammar[][] getRules() {
		return new Grammar[][]{ {new Expr()} };
	}


}
