create or replace function update_quantity_total_after_harvest_tree()
    returns trigger as
$$
begin

    update harvests h
    set quantity_total = (select coalesce(sum(hs.quantity), 0)
                          from harvest_history hs
                          where hs.harvest_id = h.id)
    where h.id = new.harvest_id;
    return new;
end;
$$ language plpgsql;

create trigger update_total_harvest_quantity
    after insert or delete or update on harvest_history
    for each row
execute function update_quantity_total_after_harvest_tree();