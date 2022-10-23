/**
 * Interface that provides abstract classes for boolean algebra operations.
 * 
 * @author Javier Yong (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
interface Cond {
  boolean eval();
  
  Cond neg();
}
