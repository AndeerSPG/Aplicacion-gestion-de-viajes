package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import dataAccess.DataAccess;
import domain.Driver;
import domain.Reseñas;
//import domain.Booking;
import domain.Ride;
import domain.RideSolicitado;
import domain.Viajecompuesto;
import domain.viajero;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {

	/**
	 * This method returns all the cities where rides depart
	 * @return collection of cities
	 */
	@WebMethod public List<String> getDepartCities();

	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city
	 *
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	@WebMethod public List<String> getDestinationCities(String from);


	/**
	 * This method creates a ride for a driver
	 *
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride
	 * @param nPlaces available seats
	 * @param driver to which ride is added
	 *
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail) throws RideMustBeLaterThanTodayException, RideAlreadyExistException;


   public Viajecompuesto crearviajecompuesto( String from, String to, Date date, int nPlaces, float price, String driverEmail, ArrayList<String> paradasIntermedias) throws RideMustBeLaterThanTodayException, RideAlreadyExistException;

	/**
	 * This method retrieves the rides from two locations on a given date
	 *
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride
	 * @return collection of rides
	 */
	@WebMethod public List<Ride> getRides(String from, String to, Date date);

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date of the month for which days with rides want to be retrieved
	 * @return collection of rides
	 */
	@WebMethod public List<Date> getThisMonthDatesWithRides(String from, String to, Date date);

	@WebMethod public List<Ride> getAllRides();

	@WebMethod public Ride encontrarViaje(List<Ride> listaDeViajes, String origen, String destino, Date fecha, Driver driver, float plazas, float precio);
	//@WebMethod public float updateRide(int rideNumber, float numAsientos);

	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod public void initializeBD();
	
public Ride cogerVueloPadre(RideSolicitado vueloHijo, Driver driver);
	
	public float updateRide(int rideNumberPadre, float plazasSolicitadas);
	
	public void storeRideSolicitado(RideSolicitado rideSolicitado);

	public DataAccess get_database();

	public void hacersemiembro(viajero v);
	
	public List<Reseñas> getReseñas(Driver d);
	
	public void guardarReseña(Reseñas res);

}