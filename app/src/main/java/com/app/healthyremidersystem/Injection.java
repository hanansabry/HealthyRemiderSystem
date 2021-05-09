package com.app.healthyremidersystem;

import com.app.healthyremidersystem.data.AppointmentRepository;
import com.app.healthyremidersystem.data.MedicalHistoryRepository;
import com.app.healthyremidersystem.data.MedicineRepository;
import com.app.healthyremidersystem.data.UserRepository;
import com.app.healthyremidersystem.data.WeeklyReportRepository;
import com.app.healthyremidersystem.domain.AppointmentRepositoryImpl;
import com.app.healthyremidersystem.domain.MedicalHistoryRepositoryImpl;
import com.app.healthyremidersystem.domain.MedicineRepositoryImpl;
import com.app.healthyremidersystem.domain.UserRepositoryImpl;
import com.app.healthyremidersystem.domain.WeeklyReportRepositoryImpl;
import com.app.healthyremidersystem.domain.usecases.AddAppointmentUseCase;
import com.app.healthyremidersystem.domain.usecases.AddIllnessHistoryUseCase;
import com.app.healthyremidersystem.domain.usecases.AddMedicineReminderUseCase;
import com.app.healthyremidersystem.domain.usecases.AddUserUseCase;
import com.app.healthyremidersystem.domain.usecases.GetWeeklyReportUseCase;
import com.app.healthyremidersystem.domain.usecases.LoginUseCase;
import com.app.healthyremidersystem.domain.usecases.RegisterUseCase;
import com.app.healthyremidersystem.domain.usecases.RemoveMedicineUseCase;
import com.app.healthyremidersystem.domain.usecases.RetrieveMedicinesRemindersUseCase;
import com.app.healthyremidersystem.domain.usecases.RetrieveUserUseCase;

public class Injection {
    public static AddAppointmentUseCase getAddAppointmentUseCase() {
        return new AddAppointmentUseCase(getAppointmentRepository());
    }

    private static AppointmentRepository getAppointmentRepository() {
        return new AppointmentRepositoryImpl();
    }

    public static AddMedicineReminderUseCase getAddMedicineReminderUseCase() {
        return new AddMedicineReminderUseCase(getMedicineRepository());
    }

    private static MedicineRepository getMedicineRepository() {
        return new MedicineRepositoryImpl();
    }

    public static GetWeeklyReportUseCase getWeeklyReportUseCase() {
        return new GetWeeklyReportUseCase(getWeeklyReportRepository());
    }

    private static WeeklyReportRepository getWeeklyReportRepository() {
        return new WeeklyReportRepositoryImpl();
    }

    public static LoginUseCase getLoginUseCase() {
        return new LoginUseCase(getUserRepository());
    }

    private static UserRepository getUserRepository() {
        return new UserRepositoryImpl();
    }

    public static RegisterUseCase getRegisterUseCase() {
        return new RegisterUseCase(getUserRepository());
    }

    public static RetrieveMedicinesRemindersUseCase getRetrieveMedicinesRemindersUseCase() {
        return new RetrieveMedicinesRemindersUseCase(getMedicineRepository());
    }

    public static AddUserUseCase getAddUserUserCase() {
        return new AddUserUseCase(getUserRepository());
    }

    public static RemoveMedicineUseCase getRemoveMedicineUseCase() {
        return new RemoveMedicineUseCase(getMedicineRepository());
    }

    public static RetrieveUserUseCase getRetrieveUserUseCase() {
        return new RetrieveUserUseCase(getUserRepository());
    }

    public static AddIllnessHistoryUseCase getIllnessHistoryUseCase() {
        return new AddIllnessHistoryUseCase(getMedicalHistoryRepository());
    }

    private static MedicalHistoryRepository getMedicalHistoryRepository() {
        return new MedicalHistoryRepositoryImpl();
    }
}
