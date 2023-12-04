import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ventana {
    private JPanel principal;
    private JTabbedPane tabbedPane1;
    private JSpinner spId;
    private JTextField txtNombre;
    private JComboBox cboPosicion;
    private JSpinner spEdad;
    private JTextField txtRendimiento;
    private JButton btnRegistrar;
    private JList lstJugadores;
    private JButton btnListar;
    private JTextField txtAId;
    private JTextField txtANombre;
    private JComboBox cboAPosicion;
    private JTextField txtAEdad;
    private JTextField txtARendimiento;
    private JButton btnActualizar;
    private JButton btnSumar;
    private JTextField txtResultado;
    private JList lstEliminar;
    private JButton btnEliminarMenor;
    private JButton btnListarEliminar;
    private Lista equipo=new Lista();

    private void llenarJlist(){
        List<Jugador> listado=equipo.getEquipo();
        DefaultListModel dlm=new DefaultListModel();
        for(Jugador j:listado){
            dlm.addElement(j.toString());
        }
        lstJugadores.setModel(dlm);
    }

    private void llenarJlist2(){
        List<Jugador> listado=equipo.getEquipo();
        DefaultListModel dlm=new DefaultListModel();
        for(Jugador j:listado){
            dlm.addElement(j.toString());
        }
        lstEliminar.setModel(dlm);
    }
    public Ventana() {
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.parseInt(spId.getValue().toString());
                String nombre=txtNombre.getText();
                int edad=Integer.parseInt(spEdad.getValue().toString());
                float rendimiento = 0;
                try{
                    rendimiento=Float.parseFloat(txtRendimiento.getText());
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Rellene todos los campos o verifique el punto en los decimales");
                    return;
                }
                String posicion=cboPosicion.getSelectedItem().toString();
                Jugador jugador=new Jugador(id,nombre,posicion,rendimiento,edad);
                if(spId.getValue().toString().isEmpty() || txtNombre.getText().isEmpty() || spEdad.getValue().toString().isEmpty() || txtRendimiento.getText().isEmpty() || cboPosicion.getSelectedItem().toString().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Rellene todos los campos");
                    return;
                }
                if(equipo.agregar(jugador)){
                    JOptionPane.showMessageDialog(null,"Nuevo registro");
                }
                else{
                    JOptionPane.showMessageDialog(null,"No se pudo añadir el jugador");
                }
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                llenarJlist();

            }
        });
        lstJugadores.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(lstJugadores.getSelectedIndex()!=-1){
                    int indice=lstJugadores.getSelectedIndex();
                    Jugador j=equipo.getEquipo().get(indice);
                    txtAId.setText(""+j.getIdentificador());
                    txtANombre.setText(j.getNombre());
                    txtAEdad.setText(""+j.getEdad());
                    txtARendimiento.setText(""+j.getRendimiento());
                    cboAPosicion.setSelectedItem(j.getPosicion());

                }
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=Integer.parseInt(txtAId.getText());
                String nombre=txtANombre.getText();
                int edad=Integer.parseInt(txtAEdad.getText());
                float rendimiento=Float.parseFloat(txtARendimiento.getText());
                String posicion=cboAPosicion.getSelectedItem().toString();
                Jugador jugador=new Jugador(id,nombre,posicion,rendimiento,edad);
                if(equipo.actualizar(jugador)) {
                    JOptionPane.showMessageDialog(null,
                            "Elemento actualizado");
                    llenarJlist();
                }
                else
                    JOptionPane.showMessageDialog(null,
                            "No se pudo actualizar");


            }
        });
        btnSumar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtResultado.setText("La suma es: "+ equipo.sumatoria());
            }
        });
        btnListarEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equipo.ordenarRendimiento();
                llenarJlist2();
            }
        });
        btnEliminarMenor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equipo.ordenarRendimiento();
                llenarJlist2();
                Jugador aux = equipo.obtenerMenorRendimiento();

                if(aux.getRendimiento() == equipo.getEquipo().get(1).getRendimiento()){
                    JOptionPane.showMessageDialog(null, "Existen 2 o más jugadores con el mismo rendimiento. No se eliminaron de la lista.");
                }else{
                    equipo.eliminar(0);
                    JOptionPane.showMessageDialog(null,"Eliminado correctamente");
                    llenarJlist2();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
