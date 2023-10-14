package com.example.demo.controller;

import java.util.List;

import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Admin;
import com.example.demo.model.Owner;
import com.example.demo.model.Type;
import com.example.demo.model.User;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.OwnerRepository;
import com.example.demo.repository.TypeRepository;
import com.example.demo.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	TypeRepository typeRepository;

	@Autowired
	OwnerRepository ownerRepository;

	@Autowired
	AdminRepository adminRepository;

// เรียกดูข้อมูลทั้งหมดของ user
	@GetMapping("/user")
	public ResponseEntity<Object> getUser() {

		try {

			List<User> users = userRepository.findAll();
			return new ResponseEntity<>(users, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>("Integer server  error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

// ส่งข้อมูลเข้าไปใน User และ Owner	

	@PostMapping("/user/owner")
	public ResponseEntity<Object> addUserAndOwner(@RequestBody Map<String, String> request) {
		try {

			User user = new User();
			user.setUserName(request.get("userName"));
			user.setPassword(request.get("password"));

			Optional<Type> type = typeRepository.findById(1);
			if (type.isPresent()) {
				user.setType(type.get());
				User savedUser = userRepository.save(user);

				Owner owner = new Owner();
				owner.setOwnerFname(request.get("ownerFname"));
				owner.setOwnerLname(request.get("ownerLname"));
				owner.setOwnerTel(request.get("ownerTel"));
				owner.setUser(savedUser);
				user.setOwner(owner);

				ownerRepository.save(owner);

				return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Type not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

// ส่งข้อมูลไป User และ admin
	@PostMapping("/user/admin")
	public ResponseEntity<Object> addUserAndAdmin(@RequestBody Map<String, String> request) {
		try {
			User user = new User();
			user.setUserName(request.get("userName"));
			user.setPassword(request.get("password"));

			Optional<Type> type = typeRepository.findById(2);
			if (type.isPresent()) {
				user.setType(type.get());
				User savedUser = userRepository.save(user);

				Admin admin = new Admin();
				admin.setAdminFname(request.get("adminFname"));
				admin.setAdminLname(request.get("adminLname"));
				admin.setAdminTel(request.get("adminTel"));
				admin.setUser(savedUser);
				user.setAdmin(admin);

				adminRepository.save(admin);

				return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Type not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//เรียกดู UserId
	@GetMapping("/user/{userId}")
	public ResponseEntity<Object> getUserDetai(@PathVariable Long userId) {

		try {
			Optional<User> user = userRepository.findById(userId);
			if (user.isPresent()) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
//แก้ไขข้อมมูลในตาราง owner
	@PutMapping("/user/owner/{userId}")
	public ResponseEntity<Object> updateOwner(@PathVariable Long userId, @RequestBody Map<String, String> request) {
	    try {
	        Optional<User> userOptional = userRepository.findById(userId);

	        if (userOptional.isPresent()) {
	            User userToUpdate = userOptional.get();
	            Owner ownerToUpdate = userToUpdate.getOwner();

	            if (ownerToUpdate != null) {
	                // แก้ไขชื่อผู้ใช้ในตาราง user
	                userToUpdate.setUserName(request.get("userName"));

	                // แก้ไขข้อมูลเจ้าของในตาราง owner
	                ownerToUpdate.setOwnerFname(request.get("ownerFname"));
	                ownerToUpdate.setOwnerLname(request.get("ownerLname"));
	                ownerToUpdate.setOwnerTel(request.get("ownerTel"));

	                userRepository.save(userToUpdate); // บันทึกการเปลี่ยนแปลงชื่อผู้ใช้ในตาราง user
	                Owner savedOwner = ownerRepository.save(ownerToUpdate);
	                return new ResponseEntity<>(savedOwner, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Owner not found for the user", HttpStatus.NOT_FOUND);
	            }
	        } else {
	            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        System.out.print(e.getMessage());
	        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

// แก้ไขข้อมูลในตาราง admin
	@PutMapping("/user/admin/{userId}")
	public ResponseEntity<Object> updateAdmin(@PathVariable Long userId, @RequestBody Map<String, String> request) {
	    try {
	        Optional<User> userOptional = userRepository.findById(userId);

	        if (userOptional.isPresent()) {
	            User userToUpdate = userOptional.get();
	            Admin adminToUpdate = userToUpdate.getAdmin();

	            if (adminToUpdate != null) {
	                // แก้ไขชื่อผู้ใช้ในตาราง user
	                userToUpdate.setUserName(request.get("userName"));

	                // แก้ไขข้อมูลเจ้าของในตาราง admin
	                adminToUpdate.setAdminFname(request.get("adminFname"));
	                adminToUpdate.setAdminLname(request.get("adminLname"));
	                adminToUpdate.setAdminTel(request.get("adminTel"));

	                userRepository.save(userToUpdate); // บันทึกการเปลี่ยนแปลงชื่อผู้ใช้ในตาราง user
	                Admin savedAdmin = adminRepository.save(adminToUpdate);
	                return new ResponseEntity<>(savedAdmin, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("Admin not found for the user", HttpStatus.NOT_FOUND);
	            }
	        } else {
	            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        System.out.print(e.getMessage());
	        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	@DeleteMapping("/user/{userId}")
	public ResponseEntity<Object> deleteuser(@PathVariable Long userId) {
		try {
			Optional<User> founduser = userRepository.findById(userId);
			if (founduser.isPresent()) {
				User user = founduser.get();

				// ตรวจสอบและลบการอ้างอิงในตารางอื่น ๆ ก่อน
				Owner owner = user.getOwner();
				if (owner != null) {
					user.setOwner(null);
					ownerRepository.delete(owner);
				}

				Admin admin = user.getAdmin();
				if (admin != null) {
					user.setAdmin(null);
					adminRepository.delete(admin);
				}

				// ลบผู้ใช้ในตาราง user
				userRepository.delete(user);

				return new ResponseEntity<>("DELETE SUCCESS", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> loginUser(@RequestBody User loginRequest) {
		try {

			Optional<User> userFound = userRepository.findByuserName(loginRequest.getUserName());

			if (userFound.isPresent() && userFound.get().getPassword().equals(loginRequest.getPassword())) {

				userFound.get().setPassword(null);
				return new ResponseEntity<>(userFound, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Invalid credentials.", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
