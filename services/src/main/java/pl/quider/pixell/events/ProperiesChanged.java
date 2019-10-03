package pl.quider.pixell.events;

public interface ProperiesChanged {
	
	/**
	 * zmieniony zostal wspolczynnik boku do zdjecia
	 * @param newFactor
	 */
	public void onFactorChanged(double newFactor);
}
