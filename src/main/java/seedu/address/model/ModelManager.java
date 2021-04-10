package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.diet.PlanType;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntake;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.person.Person;
import seedu.address.model.user.Bmi;
import seedu.address.model.user.User;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final DietLah dietLah;
    private final UserPrefs userPrefs;
    private final UniqueFoodList uniqueFoodList;
    private final FoodIntakeList foodIntakeList;
    private final DietPlanList dietPlanList;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyDietLah dietLah, UniqueFoodList uniqueFoodList, FoodIntakeList foodIntakeList,
                        DietPlanList dietPlanList, ReadOnlyUserPrefs userPrefs, User user) {
        super();
        requireAllNonNull(dietLah, userPrefs);

        this.dietLah = new DietLah(dietLah, uniqueFoodList, foodIntakeList, user);
        this.uniqueFoodList = uniqueFoodList;
        this.foodIntakeList = foodIntakeList;
        this.dietPlanList = dietPlanList;
        this.userPrefs = new UserPrefs(userPrefs);

        logger.fine("Initializing with dietLah: " + dietLah
                + ", unique food list: " + uniqueFoodList + ", food intake list: " + foodIntakeList
                + ", diet plan list: " + dietPlanList + " and user prefs " + userPrefs);

        filteredPersons = new FilteredList<>(this.dietLah.getPersonList());
    }

    /**
     * Initializes a ModelManager.
     */
    public ModelManager() {
        this(new DietLah(), new UniqueFoodList(),
                new FoodIntakeList(), new DietPlanList(), new UserPrefs(), null);
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getDietLahFilePath() {
        return userPrefs.getDietLahFilePath();
    }

    @Override
    public void setDietLahFilePath(Path dietLahFilePath) {
        requireNonNull(dietLahFilePath);
        userPrefs.setDietLahFilePath(dietLahFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setDietLah(ReadOnlyDietLah dietLah) {
        this.dietLah.resetData(dietLah);
    }

    @Override
    public ReadOnlyDietLah getDietLah() {
        return dietLah;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return dietLah.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        dietLah.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        dietLah.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        dietLah.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedDietLah}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return dietLah.equals(other.dietLah)
                && userPrefs.equals(other.userPrefs)
                && uniqueFoodList.equals(other.uniqueFoodList)
                && foodIntakeList.equals(other.foodIntakeList)
                && filteredPersons.equals(other.filteredPersons);
    }

    //=========== UniqueFoodList Accessors =============================================================

    @Override
    public UniqueFoodList getUniqueFoodList() {
        return uniqueFoodList;
    }

    @Override
    public boolean hasFoodItem(Food food) {
        requireNonNull(food);
        return dietLah.hasFoodItem(food);
    }

    @Override
    public void addFoodItem(Food food) {
        dietLah.addFoodItem(food);
    }

    @Override
    public void updateFoodItem(Food food) {
        dietLah.updateFoodItem(food);
    }

    @Override
    public void deleteFoodItem(int index) {
        dietLah.deleteFoodItem(index);
    }

    @Override
    public String listFoodItem() {
        return dietLah.listFoodItem();
    }

    @Override
    public void addUser(User user) {
        dietLah.addUser(user);
    }

    @Override
    public boolean hasUser() {
        return dietLah.hasUser();
    }

    @Override
    public void editUser(User updateUser) {
        User oldUser = dietLah.getUser();
        User newUser = new User(updateUser.getBmi(), oldUser.getFoodList(),
                oldUser.getFoodIntakeList(), updateUser.getAge(),
                updateUser.getGender(), updateUser.getIdealWeight());
        dietLah.addUser(newUser);
    }

    @Override
    public String listUser() {
        User user = dietLah.getUser();
        Bmi bmi = user.getBmi();
        String details = "Here is your current information:\nLast Updated on: "
                + user.getLastUpdated() + "\nWeight: " + bmi.getWeight()
                + " kg\nHeight: " + bmi.getHeight() + " cm\nGender: " + user.getGender()
                + "\nIdeal Weight: " + user.getIdealWeight()
                + "kg\n\nYour current BMI: ";
        if (bmi.getWeight() <= bmi.getLowerBoundWeight()) {
            details += String.format("%.2f", bmi.calculateBmi()) + " (High Risk of Nutritional Deficiency)";
        } else if (bmi.getWeight() >= bmi.getUpperBoundWeight()) {
            details += String.format("%.2f", bmi.calculateBmi()) + " (High Risk of Obesity-related Diseases)";
        } else {
            details += String.format("%.2f", bmi.calculateBmi()) + " (Healthy range)";
        }

        return details;
    }

    @Override
    public User getUser() {
        return dietLah.getUser();
    }

    @Override
    public PlanType getUserGoal() {
        User user = dietLah.getUser();
        Bmi bmi = user.getBmi();

        if (bmi.getWeight() <= bmi.getLowerBoundWeight()) {
            return PlanType.WEIGHT_GAIN;
        } else if (bmi.getWeight() >= bmi.getUpperBoundWeight()) {
            return PlanType.WEIGHT_LOSS;
        } else {
            return PlanType.WEIGHT_MAINTAIN;
        }

    }

    @Override
    public double getUserBmi() {
        User user = dietLah.getUser();
        Bmi bmi = user.getBmi();

        return bmi.getBmi();
    }

    //=========== FoodIntakeList Accessors =============================================================

    @Override
    public Food addFoodIntake(LocalDate date, Food food) {
        return dietLah.addFoodIntake(date, food);
    }

    @Override
    public void updateFoodIntake(int index, FoodIntake foodIntake) {
        dietLah.updateFoodIntake(index, foodIntake);
    }

    @Override
    public FoodIntakeList getFoodIntakeList() {
        return foodIntakeList;
    }

    //=========== DietPlanList Accessors =============================================================

    @Override
    public DietPlanList getDietPlanList() {
        return dietPlanList;
    }

    @Override
    public void setActiveDiet(DietPlan dietPlan) {
        User user = dietLah.getUser();
        user.setActiveDietPlan(dietPlan);
    }

    @Override
    public DietPlan getActiveDiet() {
        User user = dietLah.getUser();
        return user.getActiveDietPlan();
    }

    @Override
    public HashMap<Integer, DietPlan> recommendDiets(PlanType planType) {
        HashMap<Integer, DietPlan> recommendedDiets = new HashMap<>();
        Iterator<DietPlan> iterator = dietPlanList.iterator();
        int counter = 1;
        while (iterator.hasNext()) {
            DietPlan dietPlan = iterator.next();
            if (dietPlan.getPlanType() == planType) {
                recommendedDiets.put(new Integer(counter), dietPlan);
            }
            counter++;
        }
        return recommendedDiets;
    }

    //=========== Reset App Accessors =============================================================

    /**
     * Resets the app to the dummy template data.
     */
    public void resetToTemplate() {
        this.uniqueFoodList.resetToTemplate();
        this.foodIntakeList.resetToTemplate();
        this.dietLah.resetToTemplate(uniqueFoodList, foodIntakeList);
    }

    /**
     * Resets the app to blank state.
     */
    public void resetToBlank() {
        this.uniqueFoodList.resetToBlank();
        this.foodIntakeList.resetToBlank();
        this.dietLah.resetToBlank(uniqueFoodList, foodIntakeList);
    }
}
