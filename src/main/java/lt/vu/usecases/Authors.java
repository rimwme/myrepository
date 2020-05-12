package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Author;
import lt.vu.persistence.AuthorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class Authors {
    @Inject
    private AuthorsDAO authorsDAO;

    @lombok.Getter
    @lombok.Setter
    private Author author;

    @Getter
    private List<Author> allAuthors;


    @PostConstruct
    public void init() {
        loadAllAuthors();
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        if(requestParameters.containsKey("authorId"))
        {
            Integer authorId = Integer.parseInt(requestParameters.get("authorId"));
            this.author = authorsDAO.findOne(authorId);
        }
    }

    private void loadAllAuthors(){
        this.allAuthors = authorsDAO.loadAll();
    }
}
