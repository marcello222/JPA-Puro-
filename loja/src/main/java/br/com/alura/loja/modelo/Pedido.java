package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")

public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	//marcação para atributos separar o nome no BD
	@Column(name = "valor_Total")
	private BigDecimal valorTotal;
	private LocalDate data = LocalDate.now();

	// Cardinalidade de Pedido com cliente
	@ManyToOne
	private Cliente cliente;

	// Cardinalidade de mapeamento bidirecional pois a classe itemPedido ja esta
	// mapeando com Pedido os dois lados estão se mapeando
	// Metodo mappedBy para mostrar que do outro lado tambem esta mapeado e assim
	// nao abrir nova classee.  cascade = cascata tudo que fizer em pedido faz em itemPedido salva junto
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL )
	private List<ItemPedido> itens = new ArrayList<>();

	// precisa declarar o construtor Default
	public Pedido() {
	}
	
	public Pedido(Cliente cliente) {
		super();
		this.cliente = cliente;
	}
	
	//metodo para finalizar os dois lados 
	public void adicionarItem(ItemPedido item) {
		item.setPedido(this);
		this.itens.add(item);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	
	

}
