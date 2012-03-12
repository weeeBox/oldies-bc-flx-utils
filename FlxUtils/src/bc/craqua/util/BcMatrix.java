package bc.craqua.util;

public class BcMatrix
{
	/** The value that affects the positioning of pixels along the x axis when scaling or rotating an image. */
	public float a;
	/** The value that affects the positioning of pixels along the y axis when rotating or skewing an image. */
	public float b;
	/** The value that affects the positioning of pixels along the x axis when rotating or skewing an image. */
	public float c;
	/** The value that affects the positioning of pixels along the y axis when scaling or rotating an image. */
	public float d;
	/** The distance by which to translate each point along the x axis. */
	public float tx;
	/** The distance by which to translate each point along the y axis. */
	public float ty;

	public BcMatrix()
	{
		this(1, 0, 0, 1, 0, 0);
	}
	
	/** Creates a new Matrix object with the specified parameters. */
	public BcMatrix(float a, float b, float c, float d, float tx, float ty)
	{
		setTo(a, b, c, d, tx, ty);
	}

	/** Concatenates a matrix with the current matrix, effectively combining the geometric effects of the two. 
	 *  In mathematical terms, concatenating two matrixes is the same as combining them using matrix multiplication. 
	 */
	public void concat(BcMatrix m)
	{
		concatValues(m.a, m.b, m.c, m.d, m.tx, m.ty);
	}

	/** Concatenates matrix based on the values with the current matrix, effectively combining the geometric effects of the two. 
	 *  In mathematical terms, concatenating two matrixes is the same as combining them using matrix multiplication. 
	 */
	public void concatValues(float a, float b, float c, float d, float tx, float ty)
	{
		setTo(this.a * a + this.b * c, // a 
			this.a * b + this.b * d, // b 
			this.c * a + this.d * c, // c 
			this.c * b + this.d * d, // d 
			this.tx * a + this.ty * c + tx, // tx 
			this.tx * b + this.ty * d + ty); // ty
	}
	
	/** Sets each matrix property to a value that causes a null transformation. 
	 * An object transformed by applying an identity matrix will be identical to the original. */
	public void identity()
	{
		setTo(1, 0, 0, 1, 0, 0);
	}

	/** Performs the opposite transformation of the original matrix. You can apply an inverted 
	 * matrix to an object to undo the transformation performed when applying the original matrix. */
	public void invert()
	{
		float det = a * d - c * b;
		float detInv = 1.0f / det;
		setTo(detInv * d, -detInv * b, -detInv * c, detInv * a, detInv * (c * ty - d * tx), -detInv * (a * ty - b * tx));
	}
	
	/** Applies a rotation transformation to the Matrix object. */
	public void rotate(float angle)
	{
		double radians = Math.toRadians(angle);
		
		float cosA = (float) Math.cos(radians);
		float sinA = (float) Math.sin(radians);
		concatValues(cosA, sinA, -sinA, cosA, 0, 0);
	}
	
	/** Applies a scaling transformation to the matrix. The x axis is multiplied by sx, and the y axis it is multiplied by sy. */
	public void scale(float sx, float sy)
	{
		concatValues(sx, 0, 0, sy, 0, 0);
	}

	/** Sets the members of Matrix to the specified values */
	public void setTo(float a, float b, float c, float d, float tx, float ty)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.tx = tx;
		this.ty = ty;
	}
	
	/** Translates the matrix along the x and y axes, as specified by the dx and dy parameters. */
	public void translate(float dx, float dy)
	{
		concatValues(1, 0, 0, 1, dx, dy);			
	}
	
	public String toString() 
	{
		return "(a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", tx=" + tx + ", ty=" + ty + ")";
	}		
}
