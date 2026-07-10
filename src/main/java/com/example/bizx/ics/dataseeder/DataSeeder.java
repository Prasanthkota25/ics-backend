package com.example.bizx.ics.dataseeder;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.bizx.ics.LeaveEntity.Holiday;
import com.example.bizx.ics.LeaveEntity.LeaveType;
import com.example.bizx.ics.LeaveRepository.HolidayRepository;
import com.example.bizx.ics.LeaveRepository.LeaveTypeRepository;

@Component
public class DataSeeder implements CommandLineRunner {

	private final LeaveTypeRepository leaveTypeRepo;
	private final HolidayRepository holidayRepo;

	public DataSeeder(LeaveTypeRepository leaveTypeRepo, HolidayRepository holidayRepo) {
		this.leaveTypeRepo = leaveTypeRepo;
		this.holidayRepo = holidayRepo;
	}

	@Override
	public void run(String... args) {
		seedLeaveTypes();
		seedHolidays();
	}

	// ── Leave Types ──────────────────────────────────────────
	private void seedLeaveTypes() {
		if (leaveTypeRepo.count() > 0)
			return; // already seeded, skip

		leaveTypeRepo.saveAll(List.of(leaveType("Privilege Leave", 13, false),
				leaveType("Casual / Sick Leave", 8, false), leaveType("Adoption Leave", 0, true),
				leaveType("Bereavement Leave", 3, false), leaveType("Paternity Leave", 5, false),
				leaveType("Maternity Leave", 182, false), leaveType("LOP", 365, false)));

		System.out.println("✅ Leave types seeded.");
	}

	private LeaveType leaveType(String name, int days, boolean dynamic) {
		LeaveType lt = new LeaveType();
		lt.setName(name);
		lt.setDefaultDays(days);
		lt.setDynamic(dynamic);
		lt.setActive(true);
		return lt;
	}

	// ── Holidays ─────────────────────────────────────────────
	private void seedHolidays() {
		if (holidayRepo.count() > 0)
			return; // already seeded, skip

		holidayRepo.saveAll(List.of(holiday("2026-01-01", "New Year", 2026), holiday("2026-01-14", "Pongal", 2026),
				holiday("2026-01-26", "Republic Day", 2026), holiday("2026-03-30", "Good Friday", 2026),
				holiday("2026-04-14", "Tamil New Year", 2026), holiday("2026-05-01", "Labour Day", 2026),
				holiday("2026-08-15", "Independence Day", 2026), holiday("2026-10-02", "Gandhi Jayanti", 2026),
				holiday("2026-10-20", "Dussehra", 2026), holiday("2026-11-04", "Diwali", 2026),
				holiday("2026-11-05", "Diwali Holiday", 2026), holiday("2026-12-25", "Christmas", 2026)));

		System.out.println("✅ Holidays seeded.");
	}

	private Holiday holiday(String date, String name, int year) {
		Holiday h = new Holiday();
		h.setHolidayDate(LocalDate.parse(date));
		h.setName(name);
		h.setYear(year);
		return h;
	}
}
