package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.AppointmentModel;

import java.time.LocalDate;
import java.util.Set;

public class DailyViewView extends AbstractModelAndView {

    public DailyViewView(Set<AppointmentModel> morning, Set<AppointmentModel> afternoon, LocalDate day) {
        super("index_daily-view", "day", day);
        addObject("morning", morning);
        addObject("afternoon", afternoon);
    }
}
