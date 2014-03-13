package parser;

public class PseudoClass {
        private String name;
        private int id;
        private int score;
        
        public void setName(String s) {
                this.name = s;
        }
        
        public String getName() {
                return this.name;
        }
        
        public void setId(int n) {
                this.id = n;
        }
        
        public int getId() {
                return this.id;
        }

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}
}
