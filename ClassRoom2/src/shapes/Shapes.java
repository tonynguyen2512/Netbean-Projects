package shapes;

public abstract class Shapes {
	abstract public double circumstances ();
	abstract public double area ();
}

class Circle extends Shapes {
	double r;
	public Circle (double rr) { r = rr; }
	public double circumstances() {return 2 * Math.PI * r; }
	public double area () { return Math.PI * r; }
}

class Rect extends Shapes {
	double l, w;
	public Rect (double ll, double ww) {
		l = ll;
		w = ww;
	}
	public double circumstances () { return 2 * (l + w); }
	public double area () { return l*w; }
}

class Program {
	public static void main( String [] args) {
		Shapes s = new Circle (5);
		System.out.println(s.area());
	}
}