package org.ws.service.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ws.service.entities.AOrder;
import org.ws.service.entities.ARole;
import org.ws.service.entities.AService;
import org.ws.service.entities.AUser;
import org.ws.service.entities.Category;
import org.ws.service.entities.Observation;
import org.ws.service.entities.OrderService;
import org.ws.service.entities.Paragraph;
import org.ws.service.entities.Town;
import org.ws.service.entities.Visual;
import org.ws.service.enums.EnumServiceStatus;
import org.ws.service.enums.EnumVisualType;
import org.ws.service.repositories.AOrderRepository;
import org.ws.service.repositories.ARoleRepository;
import org.ws.service.repositories.AServiceRepository;
import org.ws.service.repositories.AUserRepository;
import org.ws.service.repositories.CategoryRepository;
import org.ws.service.repositories.ObservationRepository;
import org.ws.service.repositories.OrderServiceRepository;
import org.ws.service.repositories.ParagraphRepository;
import org.ws.service.repositories.TownRepository;
import org.ws.service.repositories.VisualRepository;

import jakarta.transaction.Transactional;


@Transactional
@Service
public class IServiceInitImpl implements IServiceInit {
	
	@Autowired
	public TownRepository townRepository;
	@Autowired
	public AServiceRepository aServiceRepository;
	@Autowired
	public AUserRepository aUserRepository;
	@Autowired
	public ARoleRepository aRoleRepository;
	@Autowired
	public OrderServiceRepository orderServiceRepository;
	@Autowired
	public ObservationRepository observationRepository;
	@Autowired
	public ParagraphRepository paragraphRepository;
	@Autowired
	public VisualRepository visualRepository;
	@Autowired
	public AOrderRepository aOrderRepository;
	@Autowired
	public CategoryRepository categoryRepository;
	
	public void recursiveService(AService service,Long level) {
		
		if(level>=0) {
			for(int i=0;i<(2+(int)(Math.random()*1));i++) {
				
				AService sservice= new AService();
				sservice.setLevel(service.getLevel()+1);
				service.setTitle("sous "+service.getLevel()+1+""+service.getTitle());
				sservice.setAvailable(true);
				sservice.setService(service);
				for(int j=0;j<(3+(int)(Math.random()*1));j++) {
					Paragraph paragraph = new Paragraph();
					paragraph.setName("paragraph "+j);
					paragraph.setDescription("WISE SOFT est une start-up  multi services Camerounaise exerçant dans les domaines du digital tel que : developpement web et mobile , informatique bureautique,  infographie et vidéographie, maintenance informatique, telechargements des fichiers multimédia, prestation des services etc...");
					paragraph.setService(sservice);
					for(int k=0;k<=2;k++) {
						Visual visual = new Visual();
						visual.setName("visual "+k);
						visual.setTitle("visual "+k);
						visual.setType(EnumVisualType.IMAGE);
						visual.setParagraph(paragraph);
						visualRepository.save(visual);
						//paragraph.getVisuals().add(visual1);
					}
					paragraphRepository.save(paragraph);
					//sservice.getParagraphs().add(paragraph1);
				}
				AService sservice1=aServiceRepository.save(sservice);
				//service.getServices().add(sservice1);
				recursiveService(sservice1,level-1);
			}
			aServiceRepository.save(service);
		}
	}

	@Override
	public void initCategory() {
		
		Stream.of("logiciel","bureatique","e-commerce","infographie","formation","autre").forEach(categoryName->{
			Category category = new Category();
			category.setName(categoryName);
			categoryRepository.save(category);
			});
		
	}

	@Override
	public void initService() {
		
		categoryRepository.findAll().forEach(category->{
			AService service = new AService();
			service.setLevel((long) 0);
			service.setTitle(category.getName());
			service.setAvailable(true);
			service.setCategory(category);
			recursiveService(aServiceRepository.save(service),(long) 2);
			//aServiceRepository.save(service);
		});
		
	}

	@Override
	public void initTown() {
		
		Stream.of("yaounde","douala","kribe","bandjoun","autre").forEach(townName->{
			Town town = new Town();
			town.setName(townName);
			townRepository.save(town);
			});
	
	}

	@Override
	public void initOrder() {
		List<AUser> users = aUserRepository.findAll();
		List<AService> services=new ArrayList<>();
		List<Town> towns= townRepository.findAll();
		aServiceRepository.findAll().forEach(service->{
			if(service.getLevel()==1) {
				services.add(service);
			}
		});
		for(int i=0; i<100; i++) {
			AUser user = users.get(new Random().nextInt(users.size()));
			AOrder order = new AOrder();
			order.setBillPrinte(false);
			order.setClientLastName(user.getFirstName());
			order.setIdUser(user.getId());
			order.setTown(towns.get(new Random().nextInt(towns.size())));
			order.setTotals(30000);
			//AOrder order1 = aOrderRepository.save(order);
			for(int j=0;j<5;j++) {
				OrderService orderService = new OrderService();
				orderService.setService(services.get(new Random().nextInt(services.size())));
				orderService.setOrder(order);
				orderService.setBeginDate(new Date());
				orderService.setPrice(1000);
				orderService.setQuantity(2+(int)(Math.random()*11));
				orderService.setStatus(EnumServiceStatus.STUDY);
                orderServiceRepository.save(orderService);
				Observation observation = new Observation();
				observation.setDate(new Date());
				observation.setDescription("merci d'avoir choisi notre societe");
                observation.setOrder(order);
                observationRepository.save(observation);
			}
			aOrderRepository.save(order);
	
		}
		
	}

	@Override
	public void initUser() {
		
		Stream.of("kamga","sado","andre","delon","nguigne","mervelle").forEach(userName->{
			AUser user = new AUser();
			user.setFirstName(userName);
			user.setLastName(userName);
			user.setUsername(user.getFirstName()+user.getLastName());
			user.setActive(true);
			user.setPassword("123");
			user.setEmail("andokamga@gmail.com");
			if(userName.compareTo("kamga")==0) {
				for(int y=0; y<3; y++) {
					ARole role = aRoleRepository.findById((long) (y+1)).get();
					user.getRoles().add(role);
					role.getUsers().add(user);
				}
				aUserRepository.save(user);
					
			}else if(userName.compareTo("sado")==0) {
				for(int y=0; y<2; y++) {
					ARole role = aRoleRepository.findById((long) (y+2)).get();
					user.getRoles().add(role);
					role.getUsers().add(user);
				}
				aUserRepository.save(user);
			}else {
				ARole role = aRoleRepository.findById((long) (3)).get();
				user.getRoles().add(role);
				role.getUsers().add(user);
				aUserRepository.save(user);
			}
			
		});		
		
		
	}

	@Override
	public void initRole() {
		
		Stream.of("admin","seller","user").forEach(userName->{
			ARole role = new ARole();
			role.setName(userName);
			aRoleRepository.save(role);
			});
		
	}

}
