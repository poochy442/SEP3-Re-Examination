using System;
using System.Collections.Generic;
using System.Text;

namespace DataClasses
{
    [Serializable]
    public class Campus
    {
        public int Id { get; set; }
        public string City { get; set; }
        public int PostalCode { get; set; }
        public string Address { get; set; }
        public Campus(
            int id,
            string city,
            int postalCode,
            string address)
        {
            Id = id;
            City = city;
            PostalCode = postalCode;
            Address = address;
        }
    }
}
