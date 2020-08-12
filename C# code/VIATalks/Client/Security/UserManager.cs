using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Cryptography.KeyDerivation;

namespace Client.Security
{
    public class UserManager
    {
        private static UserManager instance { get; set; }
        public bool LoggedIn { get; set; }
        public UserPrivilege Access { get; set; }

        public enum UserPrivilege
        {
            Admin,
            User
        }

        public static UserManager GetInstance()
        {
            if(instance == null)
                instance = new UserManager();

            return instance;
        }

        public static string HashPassword(string password)
        {
            // Generate a 128-bit salt
            byte[] salt = new byte[128 / 8];
            using (var rng = System.Security.Cryptography.RandomNumberGenerator.Create())
            {
                rng.GetBytes(salt);
            }

            // Hash the password
            string hashedPass = Convert.ToBase64String(KeyDerivation.Pbkdf2(
                password: password,
                salt: salt,
                prf: KeyDerivationPrf.HMACSHA1,
                iterationCount: 10000,
                numBytesRequested: 256 / 8));

            return hashedPass;
        }

        [Serializable]
        public struct User
        {
            public string Email { get; set; }
            public string Password { get; set; }
            public User(string email, string password)
            {
                Email = email;
                Password = password;
            }
        }
    }
}
