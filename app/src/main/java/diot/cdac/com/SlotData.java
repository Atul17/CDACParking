package diot.cdac.com;

public class SlotData {

    public String sl_name;
    public long book_status,slot_status;

    public SlotData(String sl_name, long book_status, long slot_status) {
        this.sl_name = sl_name;
        this.book_status = book_status;
        this.slot_status = slot_status;
    }

    public SlotData() {
    }

    public String getsl_name() {
        return sl_name;
    }

    public void setsl_name(String sl_name) {
        this.sl_name = sl_name;
    }

    public long getBook_status() {
        return book_status;
    }

    public void setBook_status(long book_status) {
        this.book_status = book_status;
    }

    public long getSlot_status() {
        return slot_status;
    }

    public void setSlot_status(long slot_status) {
        this.slot_status = slot_status;
    }
}
