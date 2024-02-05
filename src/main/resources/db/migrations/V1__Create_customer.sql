CREATE TABLE IF NOT EXISTS customers (
    id serial primary key,
    name text not null,
    loan integer not null,
    yearlyInterest integer not null,
    years integer not null,
    monthlyRate integer not null
);

