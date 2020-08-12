using System;
using System.Collections.Generic;
using System.Text;

namespace Shared
{
    [Serializable]
    class University
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Country { get; set; }

        public University(int id, string name, string country)
        {
            Id = id;
            Name = name;
            Country = country;
        }
    }
}
