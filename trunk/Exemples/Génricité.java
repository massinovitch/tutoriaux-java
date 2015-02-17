public class Solo<T> {
 
        /**
         * Variable d'instance
         */
        private T valeur;
        
        /**
         * Constructeur par défaut
         */
        public Solo(){
                this.valeur = null;
        }
        
        /**
         * Constructeur avec paramètre
         * Inconnu pour l'instant
         * @param val
         */
        public Solo(T val){
                this.valeur = val;
        }
        
        
        /**
         * Définit la valeur avec le paramètre
         * @param val
         */
        public void setValeur(T val){
                this.valeur = val;
        }
        
        /**
         * retourne la valeur déjà "castée" par la signature de la méthode !
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
