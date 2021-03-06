package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import model.DataBase;
import model.Runner;
import view.AddRunner;
import view.SeeRunner;
import view.WMadridRockRoll;

public class ControllerMadridRockRoll implements ActionListener {
	
	WMadridRockRoll w;
	AddRunner pAdd;
	SeeRunner pSee;
	DataBase data;
	private int contRunners;
	private int contRuMan;
	private int contRuWoman;
	
	
	

	public ControllerMadridRockRoll(WMadridRockRoll w, AddRunner pAdd, SeeRunner pSee) {
		this.w = w;
		this.pAdd = pAdd;
		this.pSee = pSee;
		data = new DataBase();
		
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			if (e.getActionCommand().equals(WMadridRockRoll.ITEM_ADD)) {
				w.uploadPanel(pAdd);
				
			}else if (e.getActionCommand().equals(WMadridRockRoll.ITEM_CONSULT)) {
				w.uploadPanel(pSee);
				pSee.hacerVisible(false);
			}else if (e.getActionCommand().equals(WMadridRockRoll.ITEM_SALIR)) {
				
				int option = JOptionPane.showConfirmDialog(w, "¿Estas seguro que deseas salir?", "Confirmar Salida", 
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			
			
			
			
			
		}else if (e.getSource() instanceof JButton) {
			JButton btn = (JButton)e.getSource();
			
			if (btn == pAdd.getBtnAdd()) {
				Runner rn = pAdd.requestData();
				if (rn != null) {
					if (rn.getSexo().equals(AddRunner.RDBTN_WOMAN)) {
						contRuMan++;
					}else {
						contRuWoman++;
					}
					data.addCorredor(rn);
					contRunners++;
					
					JOptionPane.showMessageDialog(pAdd, "Corredor añadido", "Resultado de operación", JOptionPane.INFORMATION_MESSAGE);	
					pAdd.cleanForm();
				}
				
			}else if (btn == pAdd.getBtnClean()) {
				
				pAdd.cleanForm();
				
			}else if (btn == pSee.getBtnSee()) {
				if (data.getListRunners().isEmpty()) {
					JOptionPane.showConfirmDialog(pSee, "No hay datos cargados", "Error de datos", JOptionPane.ERROR_MESSAGE);
				
				}else {
					if (pSee.getRdbtnAll().isSelected()) {
						pSee.fillTable(data.getListRunners());
						pSee.setLblRunners("Corredores confirmados: " + contRunners);
						pSee.hacerVisible(true);
						
					}else if (pSee.getRdbtnSeeMan().isSelected()) {
						pSee.fillTable(data.getListFilter(AddRunner.RDBTN_MAN));
						pSee.setLblRunners("Corredores hombres confirmados: " + contRuMan);
					}else if (pSee.getRdbtnSeeWoman().isSelected()) {
						pSee.fillTable(data.getListFilter(AddRunner.RDBTN_WOMAN));
						pSee.setLblRunners("Corredoras mujeres confirmadas: " + contRuWoman);
					}
					
				}
					
	
				
			}
			
		
		}

	}

}
