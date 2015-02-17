public class ClassePrincipale8 {
public class ClasseInterne {
}
public static void main(String[] args) {
ClassePrincipale8 cp = new ClassePrincipale8();
ClassePrincipale8.ClasseInterne ci = cp. new ClasseInterne() ;
System.out.println(ci.getClass().getName());
}
}