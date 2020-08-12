using System;
using System.Collections.Generic;
using System.Text;

namespace Shared
{
    [Serializable]
    class Host
    {
        public int Dd { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public string Telephone { get; set; }

        public Host(int dd, string firstName, string lastName, string email, string telephone)
        {
            Dd = dd;
            FirstName = firstName;
            LastName = lastName;
            Email = email;
            Telephone = telephone;
        }
    }
}
