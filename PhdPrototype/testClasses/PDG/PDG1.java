package examples.pdg;

public class PDG1 {
	public int i;
	private int[] array;
public int[] getArray(){
	return array;
}
	public void setI(int i) {
		//method that changes the state of this
		this.i = i;
	}

	public void m(String param) {
		param = i + "";
		PDG2 pdg = new PDG2();
//Aqui en i=.... adem�s de que el atributo i es modifica , el estado de this es modificado
		i = pdg.pdg.array[8] = pdg.pdg.i;
		System.out.println(this.i = array[pdg.pdg.i]);
		{
			int i = param.length();
			array[i] = array[this.i];
		}
		pdg.pdg = this;
		while (i == 6) {
			int i = array[8] = (pdg.pdg.i * 8) + 5 ^ param.length();
			pdg.tensor[this.i = 5][2 + 3][5].i = (param = "" + (i = array[i])).length();
			array = new int[i];
		}
		pdg.m(this);
		pdg.m2(this);
	}
}