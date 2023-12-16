package HOTELRES;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Main {
	
	private static List<Reservation> reservations = new ArrayList<>();
	private static List<Bills> bills = new ArrayList<>();
	private static List<Employees> employees = new ArrayList<>();
    private static int numReservations = 0;
    
    private static final String CSV_FILE_PATH = "reservations.csv";
    

	
	public static void main(String[] args) {
        
        JFrame frame = new JFrame("Reservation Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu newMenu = new JMenu("New");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem reservationItem = new JMenuItem("Reservation");
        JMenuItem extraServiceItem = new JMenuItem("Extra Service");
        JMenuItem contentsItem = new JMenuItem("Contents");
        JMenuItem aboutItem = new JMenuItem("About");

        fileMenu.add(exitItem);
        newMenu.add(reservationItem);
        newMenu.add(extraServiceItem);
        helpMenu.add(contentsItem);
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(newMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);

        
        JButton displayButton = new JButton("Display Reservations");
        JButton displayExtraServicesButton = new JButton("Display Extra Services");
        JButton displayReservationsForCityButton = new JButton("Disp. Res. For City");
        JButton multiThreadSearchButton = new JButton("Multithread Search");
        JButton loadReservationsButton = new JButton("Load Reservations");
        JButton saveReservationsButton = new JButton("Save Reservations");
        



        JTextArea textArea = new JTextArea();
        
        
        frame.add(displayButton);
        frame.add(displayExtraServicesButton);
        frame.add(displayReservationsForCityButton);
        frame.add(multiThreadSearchButton);
        frame.add(loadReservationsButton);
        frame.add(saveReservationsButton);
        frame.add(textArea);
        
        displayButton.setBounds(150, 20, 200, 25);
        displayExtraServicesButton.setBounds(380, 20, 200, 25);
        displayReservationsForCityButton.setBounds(150, 50, 200, 25);
        multiThreadSearchButton.setBounds(380, 50, 200, 25);
        loadReservationsButton.setBounds(380, 440, 200, 25);
        saveReservationsButton.setBounds(150, 440, 200, 25);
        textArea.setBounds(25, 85, 680, 340);

  

        
        
        reservationItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String hotelName = JOptionPane.showInputDialog(frame, "Enter hotel name:");

            	Room room = null;
            	while (true) {
            	    try {
            	        
            	        String roomTypeString = JOptionPane.showInputDialog(frame, "Enter room type (Single, Double, Club, Family, Family with view, Suite):");

            	        
            	        switch (roomTypeString) {
            	            case "Single":
            	                room = new Single();
            	                break;
            	            case "Double":
            	                room = new Double();
            	                break;
            	            case "Club":
            	                room = new Club();
            	                break;
            	            case "Family":
            	                room = new Family();
            	                break;
            	            case "Family with view":
            	                room = new FamilyView();
            	                break;
            	            case "Suite":
            	                room = new Suite();
            	                break;
            	            default:
            	                throw new RoomTypeException("Invalid room type: " + roomTypeString);
            	        }
            	        break;
            	    } catch (RoomTypeException e1) {
            	        JOptionPane.showMessageDialog(frame, e1.getMessage());
            	    }
            	}

            	int reservationStart = 0;
            	while (true) {
            	    String reservationStartString = JOptionPane.showInputDialog(frame, "Enter reservation start day:");
            	    try {
            	        reservationStart = Integer.parseInt(reservationStartString);
            	        break; 
            	    } catch (NumberFormatException e1) {
            	        JOptionPane.showMessageDialog(frame, "Reservation start day must be a numeric value!");
            	    }
            	}

            	int reservationEnd = 0;
            	while (true) {
            	    String reservationEndString = JOptionPane.showInputDialog(frame, "Enter reservation end day:");
            	    try {
            	        reservationEnd = Integer.parseInt(reservationEndString);
            	        break; 
            	    } catch (NumberFormatException e1) {
            	        JOptionPane.showMessageDialog(frame, "Reservation end day must be a numeric value!");
            	    }
            	}

            	String reservationMonth = JOptionPane.showInputDialog(frame, "Enter reservation month:");


            	Reservation reservation = new Reservation(room, hotelName, reservationMonth, reservationStart, reservationEnd, numReservations + 1);
            	reservations.add(reservation);
            	numReservations++;
            	Reservation.setTotalNumOfReservations(1);
            }
        });
        
        
        extraServiceItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String serviceTypeString = JOptionPane.showInputDialog(frame, "Enter the service type (1 for Laundry, 2 for Spa):");
                int serviceType;
                try {
                    serviceType = Integer.parseInt(serviceTypeString);
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(frame, "Invalid service type.");
                    return;
                }
                
                String reservationIdString = JOptionPane.showInputDialog(frame, "Type the reservation ID to credit this service:");
                int reservationId;
                try {
                    reservationId = Integer.parseInt(reservationIdString);
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(frame, "Invalid reservation ID.");
                    return;
                }
                
                Reservation reservation = null;
                for (Reservation r : reservations) {
                    if (r.getCustomerId() == reservationId) {
                        reservation = r;
                        break;
                    }
                }
                if (reservation == null) {
                    JOptionPane.showMessageDialog(frame, "Reservation with ID " + reservationId + " not found.");
                    return;
                }
                
                switch (serviceType) {
                    case 1:
                        while (true) {
                            String numClothesString = JOptionPane.showInputDialog(frame, "How many pieces of clothing?");
                            int numClothes;
                            try {
                                numClothes = Integer.parseInt(numClothesString);
                            } catch (NumberFormatException e1) {
                                JOptionPane.showMessageDialog(frame, "Invalid clothing count. Please enter a numeric value.");
                                continue;
                            }
                            Services laundryService = new Laundry(reservationId, 20, numClothes);
                            reservation.addService(laundryService);
                            JOptionPane.showMessageDialog(frame, "Laundry service added to reservation " + reservationId);
                            break;
                        }
                        break;
                    case 2:
                        while (true) {
                            String numDaysString = JOptionPane.showInputDialog(frame, "Enter number of days for Spa service:");
                            int numDays;
                            try {
                                numDays = Integer.parseInt(numDaysString);
                            } catch (NumberFormatException e1) {
                                JOptionPane.showMessageDialog(frame, "Invalid day count. Please enter a numeric value.");
                                continue;
                            }
                            Services spaService = new Spa(numDays, 100, reservationId);
                            reservation.addService(spaService);
                            JOptionPane.showMessageDialog(frame, "Spa service added to reservation " + reservationId);
                            break;
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, "Invalid service type.");
                        break;
                }	
            	
            	
            	
            }
        });
        
        
        
        
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                for (Reservation reservation : reservations) {
                    textArea.append(reservation.displayInfo() + "\n");
                }
            }
        });
        
        displayExtraServicesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	textArea.setText("");
            	for (Reservation r : reservations) {
            		for (Services service : r.getServices()) {
                    	if (service instanceof Laundry) {
                            Laundry laundry = (Laundry) service;
                            textArea.append("Reservation ID #" + r.getCustomerId() + " has " + laundry.getClothingPieces() + " pieces assigned for Laundry Service.");
                        } else if (service instanceof Spa) {
                            Spa spa = (Spa) service;
                            textArea.append("Reservation ID #" + r.getCustomerId() + " has " + spa.getDays() + " days of SPA services...");
                        }
                    }
                    }	
            	

            }
        });
        
        displayReservationsForCityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String city = JOptionPane.showInputDialog(frame, "Enter city name:");

            	boolean foundReservations = false;
            	textArea.setText("");
            	textArea.append("Reservations for " + city + " :\n");
            	for (Reservation r : reservations) {
            	    if (r.getHotelName().contains(city)) {
            	    	textArea.append(r.displayInfo() + "\n");
            	        foundReservations = true;
            	    }
            	}
            	
            	if (!foundReservations) {
                     textArea.append("No reservations found for " + city);
                }
            
            	
            }
        });
        
        multiThreadSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ( numReservations < 8) {
                    JOptionPane.showMessageDialog(frame, "Please enter at least 8 reservations - now it is only "+numReservations);
                    return;
                }
                String hotelName = JOptionPane.showInputDialog(frame, "Enter a hotel name for the multithreaded search:");
                List<Integer> matchingReservationIDs = new ArrayList<>();

                int numThreads = Runtime.getRuntime().availableProcessors();
                int reservationsPerThread = reservations.size() / numThreads;

                List<Thread> threads = new ArrayList<>();

                for (int i = 0; i < numThreads; i++) {
                    int start = i * reservationsPerThread;
                    int end = (i == numThreads - 1) ? reservations.size() : (start + reservationsPerThread);
                    List<Reservation> subset = reservations.subList(start, end);

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (Reservation r : subset) {
                                if (r.getHotelName().equals(hotelName)) {
                                    synchronized (matchingReservationIDs) {
                                        matchingReservationIDs.add(r.getCustomerId());
                                    }
                                }
                            }
                        }
                    });

                    threads.add(thread);
                    thread.start();
                }

                for (Thread thread : threads) {
                    try {
                        thread.join();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                if (matchingReservationIDs.isEmpty()) {
                	textArea.setText("");
                	textArea.append("No reservations found for the entered hotel name: " + hotelName );
                } else {
                	textArea.setText("");
                	textArea.append("Reservation IDs for hotel " + hotelName + ":\n");
                    
                    for (int reservationID : matchingReservationIDs) {
                        textArea.append(" " + reservationID);
                    }
                    
                }
            }
        });
        
        loadReservationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileReader reader = new FileReader(CSV_FILE_PATH);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    
                    Room room = null ;
                    
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split(",");
                        
         
                        String hotelName = data[1] + " " + data[0];
                        String reservationMonth = data[3];
                        
                   
                	        
                	    String roomTypeString = data[2] ;   
                	    switch (roomTypeString) {
                	            case "Single":
                	                room = new Single();
                	                break;
                	            case "Double":
                	                room = new Double();
                	                break;
                	            case "Club":
                	                room = new Club();
                	                break;
                	            case "Family":
                	                room = new Family();
                	                break;
                	            case "Family with view":
                	                room = new FamilyView();
                	                break;
                	            case "Suite":
                	                room = new Suite();
                	                break;
                	    }
                	     
                        
                        int reservationEnd = Integer.parseInt(data[5]);
                        int reservationStart = Integer.parseInt(data[4]);
                        
                    	Reservation reservation = new Reservation(room, hotelName, reservationMonth, reservationStart, reservationEnd, numReservations + 1);
                    	reservations.add(reservation);
                    	numReservations++;
                    	Reservation.setTotalNumOfReservations(1);
                    	
                    	textArea.append(reservation.displayInfo() + "\n");
                    	
                    }
                    
                    bufferedReader.close();
                    
                    JOptionPane.showMessageDialog(frame, "Reservations loaded!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error loading reservations!");

            }
        }
        });
        
        saveReservationsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    FileWriter fileWriter = new FileWriter(CSV_FILE_PATH,true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

     
                    for (Reservation reservation : reservations) {
                        String cityName = reservation.getHotelName().split(" ")[1]; 
                        String hotelName = reservation.getHotelName().split(" ")[0];
                        String reservationMonth = reservation.getReservationMonth();
                        String roomType = reservation.getRoom().getRoomType() ;
                        int reservationEnd = reservation.getReservationEnd();
                        int reservationStart = reservation.getReservationStart();
                        
                        if (isReservationExists(CSV_FILE_PATH, hotelName, cityName, reservationMonth, roomType, reservationStart, reservationEnd)) {
                            continue;  
                        }

                        bufferedWriter.write(cityName + "," + hotelName + "," + roomType +","+ reservationMonth + "," + reservationStart + "," + reservationEnd);
                        bufferedWriter.newLine();
                    }

                    bufferedWriter.close();

                    JOptionPane.showMessageDialog(frame, "Saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving reservations: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

			private boolean isReservationExists(String csvFilePath, String hotelName, String cityName, String reservationMonth,
					String roomType, int reservationStart, int reservationEnd) throws NumberFormatException, IOException {
			    FileReader fileReader = new FileReader(csvFilePath);
			    BufferedReader bufferedReader = new BufferedReader(fileReader);

			    String line;
			    while ((line = bufferedReader.readLine()) != null) {
			        String[] reservationData = line.split(",");
			        if (reservationData.length != 6) {
			            continue;  
			        }

			        String storedCityName = reservationData[0].trim();
			        String storedHotelName = reservationData[1].trim();
			        String storedRoomType = reservationData[2].trim();
			        String storedReservationMonth = reservationData[3].trim();
			        int storedReservationStart = Integer.parseInt(reservationData[4].trim());
			        int storedReservationEnd = Integer.parseInt(reservationData[5].trim());


			        if (storedHotelName.equals(hotelName) &&
			            storedCityName.equals(cityName) &&
			            storedRoomType.equals(roomType) &&
			            storedReservationMonth.equals(reservationMonth) &&
			            storedReservationStart == reservationStart &&
			            storedReservationEnd == reservationEnd) {
			            bufferedReader.close();
			            return true;  
			        }
			    }

			    bufferedReader.close();
		
				return false;
			}
        });
        
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
        
        contentsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 JOptionPane.showMessageDialog(frame, "Developer: Burak EREN\nSchool Number: 20200702005 \nEmail: burak.eren3@std.yeditepe.edu.tr");
            }
        });
        
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 JOptionPane.showMessageDialog(frame, "Developer: Burak EREN\nSchool Number: 20200702005 \nEmail: burak.eren3@std.yeditepe.edu.tr");;
            }
        });
        

        
        
        frame.setResizable(false);
        frame.setSize(750, 550);
        frame.setLayout (null);
        frame.setVisible(true);
		
		/*
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create new Reservation");
            System.out.println("2. Display all reservations");
            System.out.println("3. List the reservation for a specific city");
            System.out.println("4. Add extra services to a reservation");
            System.out.println("5. Calculate total cost for each service");
            System.out.println("6. Display the total cost of every customer");
            System.out.println("7. Add an Employee");
            System.out.println("8. Add a Bill");
            System.out.println("9. Get Monthly Balance");
            System.out.println("10. List all Services sorted based on cost");
            System.out.println("11. List all Reservations sorted based on hotel names");
            System.out.println("12. Exit");
            int selection = scanner.nextInt();
            scanner.nextLine();
            
            if (selection < 1 || selection > MenuOptions.values().length) {
                System.err.println("You entered an invalid menu option. Enter again.");
                continue;
            }

            switch (MenuOptions.values()[selection - 1]) {
                case CREATE_NEW_RESERVATION:
                    createReservation(scanner);
                    break;
                case DISPLAY_ALL_RESERVATIONS:
                    displayAllReservations();
                    break;
                case LIST_RESERVATIONS_SPECIFIC_CITY:
                	list_reservations_in_a_specific_city(scanner);
                	break;
                case ADD_EXTRA_SERVICES_RESERVATION :
                	addExtraService(scanner);
                	break;
                case CALCULATE_THE_TOTAL_COST_FOR_EACH_SERVICE:
                	calculateServiceCost(scanner);
                	break;
                case DISPLAY_THE_TOTAL_COST_OF_EVERY_CUSTOMER :
                	displayTotalCostPerCustomer();
                	break;
                case ADD_AN_EMPLOYEE :
                	Add_an_Employee(scanner);
                	break;
                case ADD_A_BILL :
                	Add_a_Bill(scanner) ;
                	break;
                case GET_MONTHLY_BALANCE :
                	Get_Monthly_Balance(scanner);
                	break;
                case LIST_ALL_SERVICES_BASED_ON_COST :
                	List_All_Services();
                	break;
                case LIST_ALL_RESERVATIONS_BASED_ON_HOTEL_NAMES :
                	List_All_Reservations();
                	break;
                case EXIT:
                    return;
            }
        }
        */
       
		
        
	}



	public static List<Bills> getBills() {
		return bills;
	}



	public static void setBills(List<Bills> bills) {
		Main.bills = bills;
	}



	public static List<Employees> getEmployees() {
		return employees;
	}



	public static void setEmployees(List<Employees> employees) {
		Main.employees = employees;
	}



	public static String getCsvFilePath() {
		return CSV_FILE_PATH;
	}

/*
	private static void createReservation(Scanner scanner) {
		System.out.print("ROOM INFOS:\r\n"
				+ "Room Type: Single, Daily Cost: 100, Room Size: 15, Has Bath: false\r\n"
				+ "Room Type: Double, Daily Cost: 180, Room Size: 30, Has Bath: false\r\n"
				+ "Room Type: Club, Daily Cost: 250, Room Size: 45, Has Bath: true\r\n"
				+ "Room Type: Family, Daily Cost: 400, Room Size: 50, Has Bath: false\r\n"
				+ "Room Type: Family With View, Daily Cost: 450, Room Size: 50, Has Bath: true\r\n"
				+ "Room Type: Suite, Daily Cost: 650, Room Size: 80, Has Bath: true \n\n");
		
		
		System.out.print("Enter hotel name: ");
        String hotelName = scanner.nextLine();

        Room room = null ;
        while (true) {
        	try {
                // prompt for room type
                System.out.print("Enter room type (Single, Double, Club, Family, Family with view, Suite): ");
                String roomTypeString = scanner.nextLine();

                // parse room type input and create room object
                
                switch (roomTypeString) {
                    case "Single":
                        room = new Single();
                        break;
                    case "Double":
                        room = new Double();
                        break;
                    case "Club":
                        room = new Club();
                        break;
                    case "Family":
                        room = new Family();
                        break;
                    case "Family with view":
                        room = new FamilyView();
                        break;
                    case "Suite":
                        room = new Suite();
                        break;
                    default:
                        throw new RoomTypeException("Invalid room type: " + roomTypeString);
                }
                break;
            } catch (RoomTypeException e) {
                System.err.println(e.getMessage());
            }
        }
        
        
        int reservationStart = 0;
        while (true) {
            try {
                System.out.print("Enter reservation start day: ");
                reservationStart = scanner.nextInt();
                break; // exit loop if input is valid
            } catch (InputMismatchException e) {
                System.err.println("Reservation start day must be a numeric value!");
                scanner.next(); // clear the invalid input
            }
        }
        
        
        int reservationEnd = 0;
        while (true) {
            try {
                System.out.print("Enter reservation end day: ");
                reservationEnd = scanner.nextInt();
                break; // exit loop if input is valid
            } catch (InputMismatchException e) {
                System.err.println("Reservation end day must be a numeric value!");
                scanner.next(); // clear the invalid input
            }
        }
        
        System.out.print("Enter reservation month: ");
        String reservationMonth = scanner.nextLine();
        scanner.next();
        
        
		// create reservation with room object
        Reservation reservation = new Reservation(room , hotelName, reservationMonth, reservationStart, reservationEnd, numReservations + 1);
        reservations.add(reservation);
        numReservations++;
        Reservation.setTotalNumOfReservations(1);
        System.out.println("Reservation ID : " + numReservations + " is created.");


 
    }

    private static void displayAllReservations() {
        for (Reservation r : reservations) {
            r.displayInfo();
        }
        if ( reservations.size()  == 0 ) {
            System.out.println("No reservations found... ");
        }
    }
        
    private static void list_reservations_in_a_specific_city(Scanner scanner) {
        System.out.print("Enter city name: ");
        String city = scanner.nextLine();
        boolean foundReservations = false;
        for (Reservation r : reservations) {
            if (r.getHotelName().contains(city)) {
                r.displayInfo();
                foundReservations = true;
            }
        }
        if (!foundReservations) {
            System.out.println("No reservations found for " + city);
        }
    }

    private static void addExtraService(Scanner scanner) {
        System.out.println("Please select one of the extra services from below:  ");
        System.out.println("1. Laundry service ");
        System.out.println("2. Spa service ");
        int serviceType = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Type the reservation ID to credit this service: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();
        
        Reservation reservation = null;
        for (Reservation r : reservations) {
            if (r.getCustomerId() == reservationId) {
                reservation = r;
                break;
            }
        }
        if (reservation == null) {
            System.out.println("Reservation with ID " + reservationId + " not found.");
            return;
        }
        
        switch (serviceType) {
            case 1:
            	while (true) {
            		try {
            			System.out.print("How many pieces of clothing? ");
                        int numClothes = scanner.nextInt();
                        scanner.nextLine();
                        Services laundryService = new Laundry(reservationId , 20 , numClothes );
                        reservation.addService(laundryService) ;
                        System.out.println("Laundry service added to reservation " + reservationId);
                        break;
                    } catch (InputMismatchException e) {
                        System.err.println("Clothing count must be a numeric value!");
                        scanner.next(); // clear the invalid input
                    }
            	}
                break;
            case 2:
            	while (true) {
            		try {
            			System.out.print("Enter number of days for Spa service: ");
                        int numDays = scanner.nextInt();
                        scanner.nextLine();
                        Services spaService = new Spa(numDays , 100 , reservationId );
                        reservation.addService(spaService);
                        System.out.println("Spa service added to reservation " + reservationId);
                        break;
                    } catch (InputMismatchException e) {
                        System.err.println("Day count must be a numeric value!");
                        scanner.next(); // clear the invalid input
                    }
            	}
                break;
            default:
                System.out.println("Invalid service type.");
                break;
        }
    }

    private static void calculateServiceCost(Scanner scanner) {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();
        
        Reservation reservation = null;
        for (Reservation r : reservations) {
            if (r.getCustomerId() == reservationId) {
                reservation = r;
                break;
            }
        }
        
        if (reservation == null) {
            System.out.println("Reservation with ID " + reservationId + " not found.");
            return;
        }
        
        System.out.println("The cost for Room booking service for Reservation ID " + reservationId + ": " + reservation.calculatePrice() );
        
        for (Services service : reservation.getServices()) {
        	
        	if (service instanceof Laundry) {
                Laundry laundry = (Laundry) service;
                System.out.println("The cost for Laundry service for Reservation ID " + reservationId + ": " + laundry.calculatePrice() );
            } else if (service instanceof Spa) {
                Spa spa = (Spa) service;
                System.out.println("The cost for Spa service for Reservation ID " + reservationId + ": " + spa.calculatePrice());
            }
        }
        
    }

    
    private static void displayTotalCostPerCustomer() {
        System.out.println("Total cost per customer:");
        for (Reservation reservation : reservations) {
            double roomCost = reservation.calculatePrice();
            double serviceCost = 0.0;
            for (Services service : reservation.getServices()) {
                serviceCost += service.calculatePrice();
            }
            double totalCost = roomCost + serviceCost;
            System.out.println("Reservation ID: " + reservation.getCustomerId() + " - Total Cost: " + totalCost );
        }
    }
    
    private static void Get_Monthly_Balance(Scanner scanner) {
    	System.out.print("Enter month : ");
    	String Month = scanner.nextLine();
    	
    	double totalRevenue = 0;
        for (Reservation reservation : reservations) {
            if (reservation.getReservationMonth().equals(Month)) {
            	System.out.println("For Reservation ID: " + reservation.getCustomerId() + ", Room booking, Service Cost: " + reservation.calculatePrice() );
                totalRevenue += reservation.getCost();
                for (Services service : reservation.getServices()) {
                	totalRevenue += service.calculatePrice();
                	if (service instanceof Laundry) {
                        Laundry laundry = (Laundry) service;
                        System.out.println("For Reservation ID: " + reservation.getCustomerId() + ", Laundry, Service Cost: " + laundry.calculatePrice() );
                    } else if (service instanceof Spa) {
                        Spa spa = (Spa) service;
                        System.out.println("For Reservation ID: " + reservation.getCustomerId() + ", Spa, Service Cost: " + spa.calculatePrice());
                    }
                }
            }
        }
        
        double bills_cost = 0;
        double employees_cost = 0;
        
        
        for (Bills bill : bills) {
            if (bill.getMonth().equals(Month)) {
            	bills_cost += bill.getCost();
            }
        }
        
        for (Employees employee : employees) {
        	employees_cost += employee.getCost();
        }
    	
        double totalExpenses = bills_cost + employees_cost ; 
        
        double balance = totalRevenue - totalExpenses;
        
        System.out.println("Total monthly income : " + totalRevenue);
        System.out.println("Total monthly bills due : " + bills_cost);
        System.out.println("Total monthly employee cost : " + employees_cost);
        System.out.println("End of month balance : " + balance);
    	
		
	}


	private static void Add_a_Bill(Scanner scanner) {
		System.out.print("Type: ");
		String Type = scanner.nextLine();
		
		double Amount = 0 ;
		while (true) {
    		try {
    			System.out.print("Amount: ");
    	        Amount = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.err.println("Bill amount must be a numeric value!");
                scanner.next(); // clear the invalid input
            }
    	}
        scanner.nextLine();
        System.out.print("Month: ");
        String Month = scanner.nextLine();
        
        Calculable Bill = new Bills(Type , Amount , Month );
        
        bills.add((Bills) Bill);
		
	}


	private static void Add_an_Employee(Scanner scanner) {
		System.out.print("Enter name: ");
		String Name = scanner.nextLine();
        System.out.print("Enter surname: ");
        String Surname = scanner.nextLine();
        System.out.print("Enter ID: ");
        int ID = scanner.nextInt();
        
		double MonthlyPayment = 0 ;
		while (true) {
    		try {
    			System.out.print("Enter Monthly Payment: ");
    			MonthlyPayment = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.err.println("Bill amount must be a numeric value!");
                scanner.next(); // clear the invalid input
            }
		}
        
        Calculable Employee = new Employees(Name, Surname , MonthlyPayment , ID );
        
        employees.add( (Employees) Employee);
 
	}

	private static void List_All_Services() {
	    List<Services> serviceList = new ArrayList<>();
	    for (Reservation r : reservations) {
	        for (Services s : r.getServices()) {
	            serviceList.add(s);
	        }
	    }
	    serviceList.sort(new CostComparator());
	    for (Services s : serviceList) {
	        if (s instanceof Laundry) {
	            ((Laundry) s).displayServiceInfo();
	        } else if (s instanceof Spa) {
	            ((Spa) s).displayServiceInfo();
	        } else {
	            System.out.println("Unknown service type!");
	        }
	    } 
	}

	private static void List_All_Reservations() {
		List<Reservation> reservationList = new ArrayList<>();
	    for (Reservation r : reservations) {
	        reservationList.add(r);
	    }
	    reservationList.sort(Reservation::compareTo);
	    for (Reservation r : reservationList) {
	        System.out.print("Hotel Name : " + r.getHotelName() + ", ") ;
	        r.displayServiceInfo();
	    }
	}
    
    
    public enum MenuOptions {
        CREATE_NEW_RESERVATION,
        DISPLAY_ALL_RESERVATIONS,
        LIST_RESERVATIONS_SPECIFIC_CITY,
        ADD_EXTRA_SERVICES_RESERVATION ,
        CALCULATE_THE_TOTAL_COST_FOR_EACH_SERVICE,
        DISPLAY_THE_TOTAL_COST_OF_EVERY_CUSTOMER,
        ADD_AN_EMPLOYEE ,
        ADD_A_BILL,
        GET_MONTHLY_BALANCE,
        LIST_ALL_SERVICES_BASED_ON_COST,
        LIST_ALL_RESERVATIONS_BASED_ON_HOTEL_NAMES,
        EXIT;
    }
*/

}
