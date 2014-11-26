import junit.framework.TestCase;

public class RationalTest extends TestCase {

    protected Rational HALF;

    protected void setUp() {
      HALF = new Rational( 1, 2 );
    }

    // Create new test
    public RationalTest (String name) {
        super(name);
    }

    public void testEquality() {
        assertEquals(new Rational(1,3), new Rational(1,3));
        assertEquals(new Rational(1,3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
    }

    // Test for nonequality
    public void testNonEquality() {
        assertFalse(new Rational(2,3).equals(
            new Rational(1,3)));
    }

    public void testAccessors() {
        assertEquals(new Rational(2,3).numerator(), 2);
        assertEquals(new Rational(2,3).denominator(), 3);
    }

    public void testRoot() {
        Rational s = new Rational( 1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) ) 
                        && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );
    }

    //New Tests
    //tests creation of Rational numbers
    public void testCreation(){
        try{
            Rational a = new Rational(1,0);
            fail("Can not have a rational number divide by 0, need a custom exception");
        } catch (ArithmeticException e){
            assertTrue(true);
        }
        try{
            Rational b = new Rational(0,0);
            fail("Can not have a rational number divide by 0, need a custom exception");
        } catch (ArithmeticException e){
            assertTrue(true);
        }
        try{
            Rational c = new Rational(0,1);
            fail("Can not have a rational number divide by 0, need a custom exception");
        } catch (ArithmeticException e){
            assertTrue(true);
        }
    }
    
    //tests if divide works(this uses multiplication too)
    public void testDivides() {
        Rational a = new Rational(1,3);
        Rational b = new Rational(5,3);
        assertEquals(a.divides(b),new Rational(1,5));
        
        //Testing divides knowing that this is improper creation
        Rational c = new Rational(1,0);
        try{
            b.divides(c);
            fail("Can not divide a number by an undefined number");
        } catch (ArithmeticException e){
            assertTrue(true);
        }
        
        
    }

    public void testDividesExceptions(){
        Rational a = new Rational(1,3);
        try {
            a.divides(null);
            fail("Supposed to throw NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }

    //Checks if getters and setters work
    public void testTolerance(){
        Rational a = new Rational(1,2);
        assertEquals(a.getTolerance(), new Rational(1,1000));
        a.setTolerance(new Rational(1,2000));
        assertEquals(a.getTolerance(), new Rational(1,2000));
    }

    public void testToString(){
        Rational a = new Rational(1,2);
        assertEquals(a.toString(),"1/2");
    }

    public void testAbs(){
        Rational a = new Rational(1,-2);
        Rational b = new Rational(-1,-2);
        Rational c = new Rational(1,2);
        Rational d = new Rational(-1,2);
        assertEquals(a.abs(), new Rational(1,2));
        assertEquals(b.abs(), new Rational(1,2));
        assertEquals(c.abs(), new Rational(1,2));
        assertEquals(d.abs(), new Rational(1,2));
    }

    public void testSRException(){
        Rational a = new Rational(-1,4);
        Rational b = null;
        try {
            b = a.root();
            fail("Cant have a root of a negative number");
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
            assertTrue(true);
        }

    }

    public void testAdd(){
        Rational a = new Rational (1,-4);
        Rational b = new Rational (1,-2);
        Rational c = new Rational (1,0);
        Rational d = new Rational (1,0);
        assertEquals(a.plus(b), new Rational (-3,4));
        try{
            c.plus(d);
            fail("You are not allowed to add two undefined numbers. Must have a custom exception");
        } catch(Exception e){
            assertTrue(true);
        }
    }

    public void testMain(){
        Rational.main(new String[0]);
    }

    public void testEquals(){
        Rational a = new Rational(1,2);
        assertEquals(a.equals(null),false);
        assertEquals(a.equals(.5),false);
    }

    public static void main(String args[]) {
        String[] testCaseName = 
            { RationalTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
}