package br.com.erudio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.erudio.dtos.BookDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "book")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "author", nullable = false, length = 180)
	private String author;

	@Column(name = "launch_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date launchDate;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false, length = 250)
	private String title;

	@Transient
	private String currency;

	@Transient
	private String environment;

    @Override
	public int hashCode() {
		return Objects.hash(author, currency, environment, id, launchDate, price, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(currency, other.currency)
				&& Objects.equals(environment, other.environment) && Objects.equals(id, other.id)
				&& Objects.equals(launchDate, other.launchDate) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title);
	}

}