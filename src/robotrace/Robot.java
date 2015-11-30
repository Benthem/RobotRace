package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2GL3.*;
import javax.media.opengl.glu.GLU;
import java.nio.FloatBuffer;

/**
* Represents a Robot, to be implemented according to the Assignments.
*/
class Robot {
    
    /** The position of the robot. */
    public Vector position = new Vector(0, 0, 0);
    
    /** The direction in which the robot is running. */
    public Vector direction = new Vector(1, 0, 0);

    /** The material from which this robot is built. */
    public final Material material;
    
    /** The parts of the Robot **/
    private String head, arms, torso, legs;

    /**
     * Constructs the robot with initial parameters.
     */
    public Robot(Material material,
                 Vector position,
                 String head, String arms, String torso, String legs) {
        this.material = material;
        this.position = position;
        this.head = head;
        this.arms = arms;
        this.torso = torso;
        this.legs = legs;

    }

    /**
     * Draws this robot (as a {@code stickfigure} if specified).
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim) {
        gl.glPushMatrix();
        gl.glMaterialfv(GL_FRONT_AND_BACK, gl.GL_DIFFUSE, FloatBuffer.wrap(material.diffuse));
        gl.glMaterialfv(GL_FRONT_AND_BACK, gl.GL_SPECULAR, FloatBuffer.wrap(material.specular));
        gl.glMaterialf(GL_FRONT_AND_BACK, gl.GL_SHININESS, material.shininess);
        gl.glTranslated(position.x(), position.y(), position.z());
        switch(head) {
//            case "wall-e" : Head.walle(gl, glu, glut, stickFigure, tAnim);
            case "stick" : Head.stick(gl, glu, glut, stickFigure, tAnim);
                break;
            case "weird" : Head.weird(gl, glu, glut, stickFigure, tAnim);
                break;
        }
        switch(arms) {
            case "wall-e" : Arms.walle(gl, glu, glut, stickFigure, tAnim);
                break;
            case "stick" : Arms.stick(gl, glu, glut, stickFigure, tAnim);
                break;
            case "weird" : Arms.weird(gl, glu, glut, stickFigure, tAnim);
        }
        switch(torso) {
            case "wall-e" : break;
            case "stick"  : Torso.stick(gl, glu, glut, stickFigure, tAnim);
                break;
            case "weird" : Torso.weird(gl, glu, glut, stickFigure, tAnim);
        }
        switch(legs) {
            case "wall-e" : Legs.walle(gl, glu, glut, stickFigure, tAnim);
                break;
            case "stick"  : Legs.stick(gl, glu, glut, stickFigure, tAnim);
                break;
            case "weird"  : Legs.weird(gl, glu,glut, stickFigure, tAnim);
        }
        
        gl.glPopMatrix();
    }
}

class Legs{
    static void walle(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim){
        //Track left
            gl.glPushMatrix();
            
            gl.glColor3f(0f, 0.5f, 1f);
            gl.glTranslatef(0.8f, -1.0f, -1.2f);
            
            gl.glBegin(GL_TRIANGLES);
                gl.glNormal3f(1f, 0f, 0f);
                gl.glVertex3f( 0.0f, 0.2f, 0.2f);
                gl.glVertex3f( 0.0f, 1.8f, 0.2f);
                gl.glVertex3f( 0.0f, 0.65f, 1.1f);
                
                gl.glNormal3f(-1f, 0f, 0f);
                gl.glVertex3f( -0.2f, 0.2f, 0.2f);
                gl.glVertex3f( -0.2f, 1.8f, 0.2f);
                gl.glVertex3f( -0.2f, 0.65f, 1.1f);
                
            gl.glEnd();
            
            gl.glBegin(GL_QUADS);
                gl.glNormal3f(0f, 1f, (float) (Math.sqrt(3)));
                gl.glVertex3f( 0.0f, 0.2f, 0.2f);
                gl.glVertex3f( -0.2f, 0.2f, 0.2f);
                gl.glVertex3f( -0.2f, 1.8f, 0.2f);
                gl.glVertex3f( 0.0f, 1.8f, 0.2f);
                
                gl.glNormal3f(0f, 0f, -1f);
                gl.glVertex3f( 0.0f, 0.2f, 0.2f);
                gl.glVertex3f( -0.2f, 0.2f, 0.2f);
                gl.glVertex3f( -0.2f, 0.65f, 1.1f);
                gl.glVertex3f( 0.0f, 0.65f, 1.1f);

                gl.glVertex3f( 0.0f, 1.8f, 0.2f);
                gl.glVertex3f( -0.2f, 1.8f, 0.2f);
                gl.glVertex3f( -0.2f, 0.65f, 1.1f);
                gl.glVertex3f( 0.0f, 0.65f, 1.1f);
            gl.glEnd();
            
            
            gl.glColor3d(1, 0.3, 0);
            //Pieces
            for(float i = 0; i < 100; i += 4){
                gl.glPushMatrix();
                if(i < 31.7334){
                    gl.glTranslatef(0f, (float) ((i/31.7334)*1.6 + 0.2), 0f);
                }
                else if(i < 42.1181){
                    gl.glTranslatef(0f, 1.8f, 0.2f);
                    gl.glTranslatef(0f, 
                                    (float) (0.2*Math.cos((1.5+((0.83333)*(i-31.7334)/10.3847))*Math.PI)),
                                    (float) (0.2*Math.sin((1.5+((0.83333)*(i-31.7334)/10.3847))*Math.PI)));
                }
                else if(i < 69.6001){
                    gl.glTranslatef(0f, 
                                    (float) (0.2*Math.cos(2.33333*Math.PI)) + 1.8f,
                                    (float) (0.2*Math.sin(2.33333*Math.PI)) + 0.2f);
                    gl.glTranslatef(0f, 
                                    (float) ((-1.15 * (i-42.1181)/27.4819)),
                                    (float) ((0.9 * (i-42.1181)/27.4819)));
                }
                else if(i < 75.8309){
                    gl.glTranslatef(0f, 
                                    0.65f, 
                                    1.1f);
                    gl.glTranslatef(0f, 
                                    (float) (0.2*Math.cos((2.33333+((0.5)*(i-69.6001)/6.2308))*Math.PI)),
                                    (float) (0.2*Math.sin((2.33333+((0.5)*(i-69.6001)/6.2308))*Math.PI)));
                }
                else if(i < 91.6977){
                    gl.glTranslatef(0f, 
                                    (float) (0.2*Math.cos(2.83333*Math.PI)) + 0.65f, 
                                    (float) (0.2*Math.sin(2.83333*Math.PI)) + 1.1f);
                    gl.glTranslatef(0f, 
                                    (float) (-0.45 *(i-75.8309)/15.8667),
                                    (float) (-0.9 * (i-75.8309)/15.8667));
                }
                else{
                    gl.glTranslatef(0f, 
                                    0.2f,
                                    0.2f);
                    gl.glTranslatef(0f, 
                                    (float) (0.2*Math.cos((2.83333+((0.666667)*(i-91.6977)/8.3022))*Math.PI)),
                                    (float) (0.2*Math.sin((2.83333+((0.666667)*(i-91.6977)/8.3022))*Math.PI)));
                }


                if(i > 31.7334 && i < 42.1181){
                    gl.glRotatef((float) (150 * (i-31.7334)/10.3847), 1f, 0f, 0f);
                }
                else if(i > 31.7334 && i < 69.6001){
                    gl.glRotatef(140, 1f, 0f, 0f);
                }
                else if(i > 31.7334 && i < 75.8309){
                    gl.glRotatef(150 + (float) (90 * (i-69.6001)/6.2308), 1f, 0f, 0f);
                }
                else if(i > 31.7334 && i < 91.6977){
                    gl.glRotatef(240, 1f, 0f, 0f);
                }
                else if(i > 31.7334 && i <= 100){
                    gl.glRotatef(240 + (float) (120 * (i-91.6977)/8.3022), 1f, 0f, 0f);
                }

                gl.glScalef(1.75f, 1f, 0.5f);
                glut.glutSolidCube(0.1f);
                gl.glPopMatrix();
            }

            //Wheels
            
            gl.glColor3f(0f, 0f, 1f);
            
            gl.glPushMatrix();
                gl.glTranslatef(-0.3f, 0.2f, 0.2f);
                gl.glRotatef(90f, 0f, 1f, 0f);
                glu.gluCylinder(glu.gluNewQuadric(), 0.18f, 0.2f, 0.5f, 24, 3 );
                gl.glPushMatrix();
                    gl.glTranslatef(0f, 0f, 0.5f);
                    glu.gluDisk(glu.gluNewQuadric(), 0f, 0.2f, 24, 3 );
                gl.glPopMatrix();
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(-0.3f, 1.8f, 0.2f);
                gl.glRotatef(90f, 0f, 1f, 0f);
                glu.gluCylinder(glu.gluNewQuadric(), 0.18f, 0.2f, 0.5f, 24, 3 );
                gl.glPushMatrix();
                    gl.glTranslatef(0f, 0f, 0.5f);
                    glu.gluDisk(glu.gluNewQuadric(), 0f, 0.2f, 24, 3 );
                gl.glPopMatrix();
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(-0.3f, 0.65f, 1.1f);
                gl.glRotatef(90f, 0f, 1f, 0f);
                glu.gluCylinder(glu.gluNewQuadric(), 0.18f, 0.2f, 0.5f, 24, 3 );
                gl.glPushMatrix();
                    gl.glTranslatef(0f, 0f, 0.5f);
                    glu.gluDisk(glu.gluNewQuadric(), 0f, 0.2f, 24, 3 );
                gl.glPopMatrix();
            gl.glPopMatrix();
        gl.glPopMatrix();
        
        //Track right
        gl.glScalef(-1f, 1f, 1f);
        gl.glPushMatrix();
            
            //creates an extruded triangle from separate vertices
            gl.glColor3f(0f, 0.5f, 1f);
            gl.glTranslatef(0.8f, -1.0f, -1.2f);
            
            gl.glBegin(GL_TRIANGLES);
                gl.glNormal3f(1f, 0f, 0f);
                gl.glVertex3f( 0.0f, 0.2f, 0.2f);
                gl.glVertex3f( 0.0f, 1.8f, 0.2f);
                gl.glVertex3f( 0.0f, 0.65f, 1.1f);
                
                gl.glVertex3f( -0.2f, 0.2f, 0.2f);
                gl.glVertex3f( -0.2f, 1.8f, 0.2f);
                gl.glVertex3f( -0.2f, 0.65f, 1.1f);
                
            gl.glEnd();
            
            gl.glBegin(GL_QUADS);
                gl.glNormal3f(-1f, -1f, -1f); 
                gl.glVertex3f( 0.0f, 0.2f, 0.2f);
                gl.glVertex3f( -0.2f, 0.2f, 0.2f);
                gl.glVertex3f( -0.2f, 1.8f, 0.2f);
                gl.glVertex3f( 0.0f, 1.8f, 0.2f);

                gl.glVertex3f( 0.0f, 0.2f, 0.2f);
                gl.glVertex3f( -0.2f, 0.2f, 0.2f);
                gl.glVertex3f( -0.2f, 0.65f, 1.1f);
                gl.glVertex3f( 0.0f, 0.65f, 1.1f);

                gl.glVertex3f( 0.0f, 1.8f, 0.2f);
                gl.glVertex3f( -0.2f, 1.8f, 0.2f);
                gl.glVertex3f( -0.2f, 0.65f, 1.1f);
                gl.glVertex3f( 0.0f, 0.65f, 1.1f);
            gl.glEnd();
            
            
            gl.glColor3d(1, 0.3, 0);
            //Track pieces
            //This follows a parametric representation of the triangular tracks.
            //The shape is a 30-60-90 triangle with the points replaced by pieces of a circle
            for(float i = 0; i < 100; i += 4){
                gl.glPushMatrix();
                if(i < 31.7334){
                    gl.glTranslatef(0f, (float) ((i/31.7334)*1.6 + 0.2), 0f);
                }
                else if(i < 42.1181){
                    gl.glTranslatef(0f, 1.8f, 0.2f);
                    gl.glTranslatef(0f, 
                                    (float) (0.2*Math.cos((1.5+((0.83333)*(i-31.7334)/10.3847))*Math.PI)),
                                    (float) (0.2*Math.sin((1.5+((0.83333)*(i-31.7334)/10.3847))*Math.PI)));
                }
                else if(i < 69.6001){
                    gl.glTranslatef(0f, 
                                    (float) (0.2*Math.cos(2.33333*Math.PI)) + 1.8f,
                                    (float) (0.2*Math.sin(2.33333*Math.PI)) + 0.2f);
                    gl.glTranslatef(0f, 
                                    (float) ((-1.15 * (i-42.1181)/27.4819)),
                                    (float) ((0.9 * (i-42.1181)/27.4819)));
                }
                else if(i < 75.8309){
                    gl.glTranslatef(0f, 
                                    0.65f, 
                                    1.1f);
                    gl.glTranslatef(0f, 
                                    (float) (0.2*Math.cos((2.33333+((0.5)*(i-69.6001)/6.2308))*Math.PI)),
                                    (float) (0.2*Math.sin((2.33333+((0.5)*(i-69.6001)/6.2308))*Math.PI)));
                }
                else if(i < 91.6977){
                    gl.glTranslatef(0f, 
                                    (float) (0.2*Math.cos(2.83333*Math.PI)) + 0.65f, 
                                    (float) (0.2*Math.sin(2.83333*Math.PI)) + 1.1f);
                    gl.glTranslatef(0f, 
                                    (float) (-0.45 *(i-75.8309)/15.8667),
                                    (float) (-0.9 * (i-75.8309)/15.8667));
                }
                else{
                    gl.glTranslatef(0f, 
                                    0.2f,
                                    0.2f);
                    gl.glTranslatef(0f, 
                                    (float) (0.2*Math.cos((2.83333+((0.666667)*(i-91.6977)/8.3022))*Math.PI)),
                                    (float) (0.2*Math.sin((2.83333+((0.666667)*(i-91.6977)/8.3022))*Math.PI)));
                }


                if(i > 31.7334 && i < 42.1181){
                    gl.glRotatef((float) (150 * (i-31.7334)/10.3847), 1f, 0f, 0f);
                }
                else if(i > 31.7334 && i < 69.6001){
                    gl.glRotatef(140, 1f, 0f, 0f);
                }
                else if(i > 31.7334 && i < 75.8309){
                    gl.glRotatef(150 + (float) (90 * (i-69.6001)/6.2308), 1f, 0f, 0f);
                }
                else if(i > 31.7334 && i < 91.6977){
                    gl.glRotatef(240, 1f, 0f, 0f);
                }
                else if(i > 31.7334 && i <= 100){
                    gl.glRotatef(240 + (float) (120 * (i-91.6977)/8.3022), 1f, 0f, 0f);
                }

                gl.glScalef(1.75f, 1f, 0.5f);
                glut.glutSolidCube(0.1f);
                gl.glPopMatrix();
            }

            //Wheels
            
            //creates wheels from a cilinder and a disk
            gl.glColor3f(0f, 0f, 1f);
            
            gl.glPushMatrix();
                gl.glTranslatef(-0.3f, 0.2f, 0.2f);
                gl.glRotatef(90f, 0f, 1f, 0f);
                glu.gluCylinder(glu.gluNewQuadric(), 0.18f, 0.2f, 0.5f, 24, 3 );
                gl.glPushMatrix();
                    gl.glTranslatef(0f, 0f, 0.5f);
                    glu.gluDisk(glu.gluNewQuadric(), 0f, 0.2f, 24, 3 );
                gl.glPopMatrix();
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(-0.3f, 1.8f, 0.2f);
                gl.glRotatef(90f, 0f, 1f, 0f);
                glu.gluCylinder(glu.gluNewQuadric(), 0.18f, 0.2f, 0.5f, 24, 3 );
                gl.glPushMatrix();
                    gl.glTranslatef(0f, 0f, 0.5f);
                    glu.gluDisk(glu.gluNewQuadric(), 0f, 0.2f, 24, 3 );
                gl.glPopMatrix();
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(-0.3f, 0.65f, 1.1f);
                gl.glRotatef(90f, 0f, 1f, 0f);
                glu.gluCylinder(glu.gluNewQuadric(), 0.18f, 0.2f, 0.5f, 24, 3 );
                gl.glPushMatrix();
                    gl.glTranslatef(0f, 0f, 0.5f);
                    glu.gluDisk(glu.gluNewQuadric(), 0f, 0.2f, 24, 3 );
                gl.glPopMatrix();
            gl.glPopMatrix();
        gl.glPopMatrix();
    }
    static void stick(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim){
        //stickfigure draws from center of torso to center of limb
        if(stickFigure){
            gl.glColor3f(0, 0, 0);
            gl.glBegin(GL_LINES);
                gl.glVertex3f(0, 0, 1.5f);
                gl.glVertex3f(0.175f, 0, 0.5f);
                gl.glVertex3f(0, 0, 1.5f);
                gl.glVertex3f(-0.175f, 0, 0.5f);
            gl.glEnd();
        } else {
            gl.glColor3f(0.8f, 0, 0);
            gl.glPushMatrix();
                //translate to left leg position
                gl.glTranslatef(-0.175f, 0, 0.5f);
                stickleg(gl, glu, glut, stickFigure, tAnim);
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(0.175f, 0, 0.5f);
                stickleg(gl, glu, glut, stickFigure, tAnim);
            gl.glPopMatrix();
        }
    }
    static void stickleg(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim) {
        gl.glScalef(0.15f, 0.15f, 1f);
        glut.glutSolidCube(1f);
    }

    public static void weird(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim) {
        if(stickFigure){
            gl.glColor3f(0, 0, 0);
            gl.glBegin(GL_LINES);
            for(float i = 0; i<2; i += 0.25){
                gl.glVertex3f(0, 0, 0.5f);
                gl.glVertex3f(0.3f, 0.9f-i, 0.35f);
                gl.glVertex3f(0.3f, 0.9f-i, 0.35f);
                gl.glVertex3f(0.443f, 0.9f-i, 0.126f);
                gl.glVertex3f(0, 0, 0.5f);
                gl.glVertex3f(-0.3f, 0.9f-i, 0.35f);
                gl.glVertex3f(-0.3f, 0.9f-i, 0.35f);
                gl.glVertex3f(-0.443f, 0.9f-i, 0.126f);
            }
            gl.glEnd();

        } else {
            gl.glPushMatrix();
                weirdlegs(gl, glu, glut, stickFigure, tAnim);
                gl.glRotatef(180, 0, 0, 1);
                weirdlegs(gl, glu, glut, stickFigure, tAnim);
            gl.glPopMatrix();
        }
    }

    static void weirdlegs(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim){
        gl.glPushMatrix();
            gl.glTranslatef(0.3f, 0.9f, 0.35f);
            for(int i = 0; i < 8; i++){
                gl.glPushMatrix();
                    gl.glRotatef(-60, 0, 1, 0);
                    gl.glPushMatrix();
                        gl.glScalef(0.05f, 0.05f, 0.3f);
                        glut.glutSolidCube(1);
                    gl.glPopMatrix();
                    gl.glTranslatef(0, 0, -0.15f);
                    gl.glRotatef(45, 0, 1, 0);
                    gl.glTranslatef(-0.008f, 0, -0.132f);
                    gl.glScalef(0.05f, 0.05f, 0.3f);
                    glut.glutSolidCube(1);
                gl.glPopMatrix();
                gl.glTranslatef(0, -0.25f, 0);
            }
        gl.glPopMatrix();
    }
}

class Arms{
    static void walle(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim){
        gl.glPushMatrix();
            gl.glColor3f(0f, 0f, 0f);
            gl.glTranslatef(0f, 0f, 0.5f);
            
            gl.glRotatef(-30f, 1f, 0f, 0f);
            gl.glPushMatrix();
                gl.glScalef(1f, 0.4f, 0.1f);
                glut.glutSolidCube(1.3f);
            gl.glPopMatrix();
            
            //Right Arm
            gl.glPushMatrix();
                gl.glColor3f(1f, 0.6f, 0.2f);
                gl.glTranslatef(-0.68f, 0.3f, 0f);
                gl.glScalef(0.3f, 1f, 0.3f);
                glut.glutSolidCube(0.6f);
                
                gl.glTranslatef(0f, 0.5f, 0f);
                glut.glutSolidCube(0.4f);
            gl.glPopMatrix();
            
            //Right hand
            gl.glPushMatrix();
                gl.glColor3f(0.7f, 0.7f, 0.7f);
                gl.glTranslatef(-0.68f, 1.0f, 0f);
                gl.glRotatef(-45f, 1f, 0.8f, 0f);
                gl.glTranslatef(0f, 0.1f, 0f);
                
                gl.glPushMatrix();
                    gl.glScalef(1f, 1f, 0.25f);
                    glut.glutSolidCube(0.2f);
                gl.glPopMatrix();
                
                gl.glTranslatef(0f, 0.1f, 0f);
                gl.glRotatef(-70f, 1f, 0f, 0f);
                gl.glTranslatef(0f, 0.1f, 0f);
                
                gl.glPushMatrix();
                    gl.glScalef(1f, 1f, 0.25f);
                    glut.glutSolidCube(0.2f);
                gl.glPopMatrix();
            gl.glPopMatrix();
            
            //Mirroring Matrix
            gl.glScalef(-1f, 1f, 1f);
            //Left arm
            gl.glPushMatrix();
                gl.glColor3f(1f, 0.6f, 0.2f);
                gl.glTranslatef(-0.68f, 0.3f, 0f);
                gl.glScalef(0.3f, 1f, 0.3f);
                glut.glutSolidCube(0.6f);
                
                gl.glTranslatef(0f, 0.5f, 0f);
                glut.glutSolidCube(0.4f);
            gl.glPopMatrix();
            
            //Left hand
            gl.glPushMatrix();
                gl.glColor3f(0.7f, 0.7f, 0.7f);
                gl.glTranslatef(-0.68f, 1.0f, 0f);
                gl.glRotatef(-45f, 1f, 0.8f, 0f);
                gl.glTranslatef(0f, 0.1f, 0f);
                
                gl.glPushMatrix();
                    gl.glScalef(1f, 1f, 0.25f);
                    glut.glutSolidCube(0.2f);
                gl.glPopMatrix();
                
                gl.glTranslatef(0f, 0.1f, 0f);
                gl.glRotatef(-70f, 1f, 0f, 0f);
                gl.glTranslatef(0f, 0.1f, 0f);
                
                gl.glPushMatrix();
                    gl.glScalef(1f, 1f, 0.25f);
                    glut.glutSolidCube(0.2f);
                gl.glPopMatrix();
            gl.glPopMatrix();
        gl.glPopMatrix();
    }
    static void stick(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim){
        if(stickFigure) {
            gl.glColor3f(0, 0, 0);
            gl.glBegin(GL_LINES);
                gl.glVertex3f(0, 0, 1.5f);
                gl.glVertex3f(0.3f, 0, 1.8f);
                gl.glVertex3f(0, 0, 1.5f);
                gl.glVertex3f(-0.3f, 0, 1.8f);
            gl.glEnd();
        } else {
            gl.glColor3f(0.3f, 0.3f, 0);
            gl.glPushMatrix();
                gl.glTranslatef(0.3f, 0, 1.8f);
                gl.glRotatef(45, 0, 1, 0);
                gl.glScalef(0.1f, 0.1f, 0.5f);
                glut.glutSolidCube(1);
            gl.glPopMatrix();
            gl.glPushMatrix();
                gl.glTranslatef(-0.3f, 0, 1.8f);
                gl.glRotatef(-45, 0, 1, 0);
                gl.glScalef(0.1f, 0.1f, 0.5f);
                glut.glutSolidCube(1);
            gl.glPopMatrix();
        }
    }
    static void weird(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim){
        //Has a tail for arms
        if(stickFigure){
            gl.glColor3f(0, 0, 0);
            gl.glBegin(GL_LINES);
                //body-tail
                gl.glVertex3f(0, 0, 0.5f);
                gl.glVertex3f(0, -1, 0.5f);
                gl.glVertex3f(0, -1, 0.5f);
                gl.glVertex3f(0, -1.3f, 0.4f);
                gl.glVertex3f(0, -1.3f, 0.4f);
                gl.glVertex3f(0, -1.5f, 0.3f);
                gl.glVertex3f(0, -1.5f, 0.3f);
                gl.glVertex3f(0, -1.575f, 0.225f);
            gl.glEnd();
        } else {
            gl.glPushMatrix();
                gl.glTranslatef(0, -1, 0.5f);
                glut.glutSolidSphere(0.3, 20, 20);
                gl.glTranslatef(0, -0.3f, -0.1f);
                glut.glutSolidSphere(0.2, 20, 20);
                gl.glTranslatef(0, -0.2f, -0.1f);
                glut.glutSolidSphere(0.1, 20, 20);
                gl.glTranslatef(0, -0.075f, -0.075f);
                glut.glutSolidSphere(0.05, 20, 20);
            gl.glPopMatrix();
        }
    }
}

class Head{
    static void stick(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim){
        if(stickFigure){
            gl.glColor3f(0, 0, 0);
            gl.glBegin(GL_LINES);
                gl.glVertex3f(0, 0, 1.5f);
                gl.glVertex3f(0, 0, 2.25f);
            gl.glEnd();
        } else {
            gl.glColor3f(0.6f, 0, 0);
            gl.glPushMatrix();
                gl.glTranslatef(0, 0, 2.15f);
                glut.glutSolidSphere(0.2, 20, 20);
            gl.glPopMatrix();
        }
    }
    static void weird(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim){
        if(stickFigure){
            gl.glColor3f(0, 0, 0);
            gl.glBegin(GL_LINES);
                gl.glVertex3f(0, 1, 0.5f);
                gl.glVertex3f(0, 1.3f, 0.7f);
                gl.glVertex3f(0, 1.3f, 0.7f);
                gl.glVertex3f(-0.175f, 1.475f, 0.9f);
                gl.glVertex3f(0, 1.3f, 0.7f);
                gl.glVertex3f(0.175f, 1.475f, 0.9f);
            gl.glEnd();
        } else {
            gl.glPushMatrix();
                gl.glTranslatef(0, 1.3f, 0.7f);
                gl.glPushMatrix();
                    gl.glScalef(0.4f, 0.4f, 0.4f);
                    glut.glutSolidRhombicDodecahedron();
                gl.glPopMatrix();
                gl.glPushMatrix();
                    gl.glTranslatef(-0.175f, 0.175f, 0.2f);
                    glut.glutSolidSphere(0.15, 20, 20);
                gl.glPopMatrix();
                gl.glPushMatrix();
                    gl.glTranslatef(0.175f, 0.175f, 0.2f);
                    glut.glutSolidSphere(0.15, 20, 20);
                gl.glPopMatrix();
            gl.glPopMatrix();
        }
    }
}

class Torso{
    static void stick(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim){
        if(stickFigure){
            //no translation so no stick
        } else {
            gl.glColor3f(1f, 0, 0);
            gl.glPushMatrix();
                gl.glTranslatef(0, 0, 1.5f);
                gl.glScalef(0.5f, 0.25f, 1f);
                glut.glutSolidCube(1f);
            gl.glPopMatrix();
        }
    }
    static void weird(GL2 gl, GLU glu, GLUT glut, boolean stickFigure, float tAnim){
        if(stickFigure){
            gl.glColor3f(0, 0, 0);
            gl.glBegin(GL_LINES);
                //body-neck
                gl.glVertex3f(0, 0, 0.5f);
                gl.glVertex3f(0, 1, 0.5f);
            gl.glEnd();
        } else {
            gl.glColor3f(0, 1f, 0);
            gl.glPushMatrix();
                gl.glTranslatef(0, 0, 0.5f);
                gl.glScalef(0.3f, 2f, 0.3f);
                gl.glRotatef(90, 1, 0, 0);
                glut.glutSolidTorus(0.5f, 1f, 20, 20);
            gl.glPopMatrix();
            //neck
            gl.glPushMatrix();
                gl.glTranslatef(0, 1, 0.5f);
                glut.glutSolidSphere(0.3, 20, 20);
            gl.glPopMatrix();
            //glut.glutSolidRhombicDodecahedron();
        }
    }
}