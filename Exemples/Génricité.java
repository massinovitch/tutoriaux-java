public class Solo<T> {
 
        /**
         * Variable d'instance
         */
        private T valeur;
        
        /**
         * Constructeur par d�faut
         */
        public Solo(){
                this.valeur = null;
        }
        
        /**
         * Constructeur avec param�tre
         * Inconnu pour l'instant
         * @param val
         */
        public Solo(T val){
                this.valeur = val;
        }
        
        
        /**
         * D�finit la valeur avec le param�tre
         * @param val
         */
        public void setValeur(T val){
                this.valeur = val;
        }
        
        /**
         * retourne la valeur d�j� "cast�e" par la signature de la m�thode !
         * @return
         */
        public T getValeur(){
                return this.valeur;
        }       

		public static void main(String[] args) {
             // TODO Auto-generated method stub
                Solo<Integer> val = new Solo<Integer>();
                Solo<String> valS = new Solo<String>("TOTOTOTO");
                Solo<Float> valF = new Solo<Float>(12.2f);
                Solo<Double> valD = new Solo<Double>(12.202568);                
		}
		
}
